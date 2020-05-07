package com.hovel.base.thread.future;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class FutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new MyPolicy());

        Future future1 = executor.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("start task 1");
                Thread.sleep(1000);
            }
        });

        Future future2 = executor.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("start task 2");
            }
        });


        Future future3 = null;
        try {
            future3 = executor.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("start task 3");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("return task 1 :" + future1.get());
        System.out.println("return task 2 :" + future2.get());
        try {
            System.out.println("return task 3 :" + future3 == null ? null : future3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


        executor.shutdown();
    }

    static class MyPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            ((FutureTask)r).cancel(true);
        }
    }
}
