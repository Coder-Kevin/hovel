package com.hovel.base.thread.synccontainer;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@ThreadSafe
@Slf4j
public class CollectionsExample {
    // 请求总数
    public static int requestTotal = 2000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);

        for (int i = 0; i < requestTotal; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(num);
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
        executorService.shutdown();
        log.info("{}", list.size());
    }

    private static void add(int i) {
        list.add(i);
    }

}
