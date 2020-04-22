package com.hovel.base.thread.pdmodel;

import java.util.*;

public class ComputerStore {

    private static volatile int count = 0;

    private static List<Computer> computers = new ArrayList<>();

    public synchronized void create(String type, String num, String name) throws InterruptedException {
        if (count == 1000) {
            System.out.println(Thread.currentThread().getName() + "发现仓库已经满了");
        }
        Computer computer = new Computer(type, num, name);
        computers.add(computer);
        count++;
        System.out.println(Thread.currentThread().getName() + " - 生产电脑：" + computer + ",理论库存：" + count + ",实际库存" + computers.size());
    }

    public synchronized void consume() {
        if (count != 0) {
            Computer computer = computers.remove(computers.size() - 1);
            count--;
            System.out.println(Thread.currentThread().getName() + " - 买走电脑：" + computer + ",理论库存：" + count + ",实际库存" + computers.size());

        }
    }

    public boolean full(){
        return count == 1000;
    }

}
