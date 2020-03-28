package com.hovel.base.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = atomicInteger.incrementAndGet();
                System.out.println("--" + i);
            }
        }).start();
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }
}
