package com.hovel.base.thread.lock;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
@Slf4j
public class LockExample {

    // 请求总数
    public static int requestTotal = 2000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);

        for (int i = 0; i < requestTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("{}", e);
                }

                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("{}", e);
        }

        log.info(String.valueOf(count));
        executorService.shutdown();
    }

    private static void add() {
        lock.lock();
        try {
            count++;
            log.info("{} : {}", Thread.currentThread().getName(), count);
        } finally {
            lock.unlock();
        }
    }
}
