package com.hovel.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkLockDemo {

    private static final String ZK_ADDRESS = "127.0.0.1:2187";
    private static final String ZK_LOCK_PATH = "/locks/lock_01";

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        log.info("zk state is {}, zk client start successfully!", client.getState());

        Thread t1 = new Thread(() -> doWithLock(client), "线程一");
        Thread t2 = new Thread(() -> doWithLock(client), "线程二");

        t1.start();
        t2.start();

    }

    private static void doWithLock(CuratorFramework client) {
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            /**
             * 使用场景：假定设置的超时时间大于业务执行时间
             * acquire入参有超时时间，加入业务执行时间大于节点超时时间就会出现问题
             */
            if (lock.acquire(200, TimeUnit.SECONDS)) {
                if (Thread.currentThread().getName().startsWith("线程一")) {
//                    Thread.currentThread().interrupt();
//                    System.exit(1);
                }
                log.info("{} hold lock", Thread.currentThread().getName());
                Thread.sleep(5000L);

            }
        } catch (Exception e) {
            log.error("异常:{}", e);
        } finally {
            try {
                lock.release();
                log.info("{} release lock", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("释放锁异常:{}", e);
            }
        }
    }


}
