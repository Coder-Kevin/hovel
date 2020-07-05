package com.hovel.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author Kevin
 */
public class ZeroEvenOdd {

    private int n;

    private volatile int count = 0;

    private static final ReentrantLock reentrantLock = new ReentrantLock();

    private static Condition zeroCondition = reentrantLock.newCondition();

    private static Condition evenCondition = reentrantLock.newCondition();

    private static Condition oddCondition = reentrantLock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count <= n) {
                printNumber.accept(0);
                zeroCondition.await();
                count++;

//                if (count > 0) {
//                    evenCondition.signal();
//                    oddCondition.signal();
//                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count <= n && count % 2 == 1) {
                printNumber.accept(count);
                count++;
                evenCondition.await();
                zeroCondition.signal();
            }

        } finally {
            reentrantLock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (count <= n && count % 2 == 0 && count > 0) {
                printNumber.accept(count);
                count++;
                oddCondition.await();
                zeroCondition.signal();
            }


        } finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        new Thread(() -> {
            try {
                zeroEvenOdd.zero(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
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
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
