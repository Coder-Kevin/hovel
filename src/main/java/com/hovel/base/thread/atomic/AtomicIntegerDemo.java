package com.hovel.base.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000);

        new Thread(() -> {
            int i = atomicInteger.incrementAndGet();
            log.info("--" + i);
        }).start();
        int i = atomicInteger.incrementAndGet();
        log.info(String.valueOf(i));
    }
}
