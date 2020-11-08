package com.hovel.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());

        Future future1 = executor.submit(() -> {
            System.out.println("start task 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("异常:{}", e);
            }
        });

        Future future2 = executor.submit(() -> log.info("start task 2"));

        try {
            Future future3 = executor.submit(() -> log.info("start task 3"));
        } catch (Exception e) {
            log.error("异常:{}", e);
        }

        log.info("return task 1 :" + future1.get());
        log.info("return task 2 :" + future2.get());

        executor.shutdown();
    }
}
