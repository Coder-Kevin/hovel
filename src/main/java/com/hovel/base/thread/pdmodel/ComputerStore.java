package com.hovel.base.thread.pdmodel;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ComputerStore {

    private static AtomicInteger count = new AtomicInteger();

    private static final List<Computer> computers = new ArrayList<>();

    private final Object lock = new Object();

    public void create(String type, String num, String name) throws InterruptedException {

        synchronized (lock) {
            log.info("生产者" + Thread.currentThread().getName() + " get lock");
            while (count.intValue() == 100) {
                log.info(Thread.currentThread().getName() + "发现仓库已经满了");
                lock.wait();
            }
            Computer computer = new Computer(type, num, name);
            computers.add(computer);
            count.addAndGet(1);
            lock.notifyAll();
            log.info(Thread.currentThread().getName() + " - 生产电脑：" + computer + ",理论库存：" + count + ",实际库存" + computers.size());
            log.info("生产者" + Thread.currentThread().getName() + " release lock");
        }
    }

    @SneakyThrows
    public void consume() {
        synchronized (lock) {
            log.info("消费者" + Thread.currentThread().getName() + " get lock");
            while (!hasProduct()) {
                lock.wait();
            }
            if (count.intValue() != 0) {
                Computer computer = computers.remove(computers.size() - 1);
                count.decrementAndGet();
                log.info(Thread.currentThread().getName() + " - 买走电脑：" + computer + ",理论库存：" + count.intValue() + ",实际库存" + computers.size());
            }

            if (!full()) {
                lock.notifyAll();
            }
            log.info("消费者" + Thread.currentThread().getName() + " release lock");
        }
    }

    public boolean full() {
        return count.intValue() == 100;
    }

    public boolean hasProduct() {
        return count.intValue() > 0;
    }

}
