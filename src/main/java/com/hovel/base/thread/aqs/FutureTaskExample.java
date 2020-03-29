package com.hovel.base.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(new MyTask());
        new Thread(futureTask).start();

        String result = futureTask.get();
        log.info("result : {}", result);

    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("execute task in callable");
            Thread.sleep(3000);
            return "Ok";
        }
    }
}
