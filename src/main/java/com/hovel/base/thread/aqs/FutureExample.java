package com.hovel.base.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyTask());

        String result = future.get();
        log.info("result : {}", result);
        executorService.shutdown();

    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("execute task in callable");
            Thread.sleep(5000);
            return "Ok";
        }
    }
}
