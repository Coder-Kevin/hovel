package com.hovel.base.thread;

/**
 * yield当前线程出让cpu资源的一个暗示，但是并不一定会执行
 */
public class YieldDemo {

    public static void main(String[] args) {
        Runnable yieldTask = new YieldTask();

        Thread t1 = new Thread(yieldTask, "A");
        Thread t2 = new Thread(yieldTask, "B");

        t1.start();
        t2.start();
    }

    static class YieldTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "---" + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        }
    }
}
