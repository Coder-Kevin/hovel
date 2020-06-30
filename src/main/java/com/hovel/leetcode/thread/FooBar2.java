package com.hovel.leetcode.thread;

public class FooBar2 {
    private int n;

    private Object lock = new Object();

    private volatile boolean f = true;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 1; i <= n; i++) {
            synchronized (lock) {
                while (f) {
                    printFoo.run();
                    f = false;
                }
                lock.wait();
                f = true;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 1; i <= n; i++) {

            synchronized (lock) {
                while (!f) {
                    printBar.run();
                    f = true;
                }
                lock.notify();
                lock.wait();
            }
        }
    }

}