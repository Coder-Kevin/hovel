package com.hovel.base.thread;

public class WaitSleepDemo {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        new Thread(() -> {
            System.out.println("Thread A is waiting for the lock");
            synchronized (lock) {
                System.out.println("Thread A get lock");
                try {
                    Thread.sleep(20);
                    System.out.println("Thread A do wait method");
                    lock.wait(1000);
                    System.out.println("Thread A done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(10);

        new Thread(() -> {

            System.out.println("Thread B is waiting for the lock");
            synchronized (lock) {
                System.out.println("Thread B get lock");
                try {
                    System.out.println("Thread B is sleeping 10 ms");
                    Thread.sleep(10);
                    System.out.println("Thread B done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
