package com.hovel.lock;

import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

public class RedisLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        final RedisLock redisLock = new RedisLock(jedis);

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            doWithLock(redisLock);
            countDownLatch.countDown();
        }, "t1");
        Thread t2 = new Thread(() -> {
            doWithLock(redisLock);
            countDownLatch.countDown();
        }, "t2");



        t1.start();
        t2.start();

        countDownLatch.await();
        jedis.close();
    }

    private static void doWithLock(RedisLock redisLock) {
        try {
            if (redisLock.lockByRedis("1", 9000)) {
                System.out.println(Thread.currentThread().getName() + " hold lock");
                Thread.sleep(5000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                redisLock.unlockByRedis("1");
                System.out.println(Thread.currentThread().getName() + " release lock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
