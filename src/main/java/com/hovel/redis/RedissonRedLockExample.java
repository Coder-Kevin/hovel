package com.hovel.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * redis的不足之处：
 * redis -> setnx key value expire-time 可以保证设置key和设置过期时间是原子的；
 * 但是这种情况是无法解决假如业务执行时间超过redis锁的过期时间就会出现问题
 * <p>
 * 针对上述情况redisson利用lua脚本和看门狗（另起一个线程检测redis锁）来解决上面问题：
 * redisson 实现了分布式锁(主要利用lua脚本，redis支持lua脚本的原子性操作)，并且做了看门狗（默认30skey过期，每10s进行检测未释放锁就会继续延长30s）
 * lock()  unlock()
 * <p>
 * 但是redis 还存在主从节点问题，假如加锁成功master会立刻通知业务执行，这时候master节点挂点还未来的及同步salve节点就会出现问题。这一点
 * zookeeper有先天优势，因为zk强一致性牺牲了部分高性能，zk会同步超过半数的节点才会通知业务执行。但从性能角度考虑，当然redis显然是更佳选择。
 * redis提供了RedLock的算法来达到上述zk的方式
 * <p>
 * redis分布式锁提升性能-分段锁
 */
@Slf4j
public class RedissonRedLockExample {

    private static RedissonClient client;

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);
        log.info("redis client start.....");


        Thread t1 = new Thread(() -> {
            doWithLock();
        }, "线程一");
        Thread t2 = new Thread(() -> {
            doWithLock();
        }, "线程二");

        t1.start();
        t2.start();

    }

    private static void doWithLock() {
        // 获取非公平锁
        RLock lock1 = client.getLock("lock01");
        RLock lock2 = client.getLock("lock01");
        RLock lock3 = client.getLock("lock01");

        RLock redissonRedLock = client.getRedLock(lock1, lock2, lock3);


        try {
//            lock.lock(2000, TimeUnit.MILLISECONDS);
            redissonRedLock.lock();
            log.info("{} hold lock", Thread.currentThread().getName());
            Thread.sleep(5000L);
        } catch (Exception e) {
            log.error("异常:{}", e);
        } finally {
            try {
                redissonRedLock.unlock();
                log.info("{} release lock", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("释放锁异常:{}", e);
            }
        }
    }


}
