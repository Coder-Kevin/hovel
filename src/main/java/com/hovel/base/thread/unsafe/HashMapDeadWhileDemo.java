package com.hovel.base.thread.unsafe;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapDeadWhileDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new MapTask().start();
        }

    }

    static class MapTask extends Thread {
        private static AtomicInteger atomicInteger = new AtomicInteger();

        private static Map map = new HashMap();

        @Override
        public void run() {
            while (atomicInteger.get() < 1000) {
                System.out.println(Thread.currentThread().getName() + " 执行中 " + atomicInteger.get());
                map.put(atomicInteger.get(), atomicInteger.get());
                atomicInteger.incrementAndGet();
            }
        }
    }


}
