package com.hovel.base.thread.pdmodel;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComputerStore {

    private static AtomicInteger count = new AtomicInteger();

    private static final List<Computer> computers = new ArrayList<>();

    private final Object lock = new Object();

    public void create(String type, String num, String name) throws InterruptedException {

        synchronized (lock) {
            System.out.println("生产者" + Thread.currentThread().getName() + " get lock");
            while (count.intValue() == 100) {
                System.out.println(Thread.currentThread().getName() + "发现仓库已经满了");
                lock.wait();
            }
            Computer computer = new Computer(type, num, name);
            computers.add(computer);
            count.addAndGet(1);
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + " - 生产电脑：" + computer + ",理论库存：" + count + ",实际库存" + computers.size());
            System.out.println("生产者" + Thread.currentThread().getName() + " release lock");
        }
    }

    @SneakyThrows
    public void consume() {
        synchronized (lock) {
            System.out.println("消费者" + Thread.currentThread().getName() + " get lock");
            while (!hasProduct()) {
                lock.wait();
            }
            if (count.intValue() != 0) {
                Computer computer = computers.remove(computers.size() - 1);
                count.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + " - 买走电脑：" + computer + ",理论库存：" + count.intValue() + ",实际库存" + computers.size());
            }

            if (!full()) {
                lock.notifyAll();
            }
            System.out.println("消费者" + Thread.currentThread().getName() + " release lock");
        }
    }

    public boolean full() {
        return count.intValue() == 100;
    }

    public boolean hasProduct() {
        return count.intValue() > 0;
    }

}
