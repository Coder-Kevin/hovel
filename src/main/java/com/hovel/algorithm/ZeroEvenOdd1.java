package com.hovel.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author Kevin
 */
public class ZeroEvenOdd1 {

    private int n;

    private volatile int count = 0;

    private static final ReentrantLock reentrantLock = new ReentrantLock();

//    private static Condition zeroCondition = reentrantLock.newCondition();

    private static Condition evenCondition = reentrantLock.newCondition();

    private static Condition oddCondition = reentrantLock.newCondition();

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count <= n) {
                if (count % 2 == 0) {
                    oddCondition.signal();
                }
                printNumber.accept(count);
                count++;
                evenCondition.await();
            }

        } finally {
            reentrantLock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count <= n) {
                if (count % 2 == 1) {
                    evenCondition.signal();
                }
                printNumber.accept(count);
                count++;
                oddCondition.await();

            }


        } finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        ZeroEvenOdd1 zeroEvenOdd = new ZeroEvenOdd1(5);

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.println(Thread.currentThread().getName() + value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.println(Thread.currentThread().getName() + value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
