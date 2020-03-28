package com.hovel.base.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchTimeoutExample {

    public final static int threadNum = 100;

    public static void main(String[] args) throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < threadNum; i++) {

            final int threadNo = i;
            service.execute(() -> {
                try {
                    test(threadNo);
                } catch (Exception e) {
                    log.error("run error: {}", e);
                } finally {
                    // 注意，尽量在finally以保证执行了下述方法
                    countDownLatch.countDown();
                }

            });
        }
        // await阻塞countdown=0为止
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        service.shutdown();
    }

    public static void test(int num) throws InterruptedException {
        Thread.sleep(30);
        log.info("test-{}", num);
    }
}
