package com.hovel.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new MyPolicy());

        Future future1 = executor.submit(() -> {
            log.info("start task 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("异常:{}", e);
            }
        });

        Future future2 = executor.submit(() -> log.info("start task 2"));


        Future future3 = null;
        try {
            future3 = executor.submit(() -> log.info("start task 3"));
        } catch (Exception e) {
            log.error("异常:{}", e);
        }

        log.info("return task 1 :" + future1.get());
        log.info("return task 2 :" + future2.get());
        try {
            log.info("return task 3 :{}", future3 == null ? null : future3.get());
        } catch (Exception e) {
            log.error("异常:{}", e);
        }

        executor.shutdown();
    }

    static class MyPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            ((FutureTask) r).cancel(true);
        }
    }
}
