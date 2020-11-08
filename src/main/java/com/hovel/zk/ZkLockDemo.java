package com.hovel.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkLockDemo {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static final String ZK_LOCK_PATH = "/locks/lock_01";

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        log.info("zk state is {}, zk client start successfully!", client.getState());

        Thread t1 = new Thread(() -> {
            doWithLock(client);
        }, "线程一");
        Thread t2 = new Thread(() -> {
            doWithLock(client);
        }, "线程二");

        t1.start();
        t2.start();

//        client.close();
    }

    private static void doWithLock(CuratorFramework client) {
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            if (lock.acquire(1000, TimeUnit.SECONDS)) {
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
