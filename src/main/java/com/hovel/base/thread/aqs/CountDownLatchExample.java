package com.hovel.base.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample {

    public final static int threadNum = 100;


    public static void main(String[] args) throws Exception {
        /**
         * CountDownLatch 阻塞某一线程（例如此案例中的mian线程）直到其他线程（CountDownLatch所含数量的）全部完成
         */
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
        countDownLatch.await();
        log.info(Thread.currentThread().getName() + " finish");
        service.shutdown();
    }

    public static void test(int num) {
        log.info("{}-{}", Thread.currentThread().getName(), num);
    }
}
