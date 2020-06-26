package com.hovel.base.thread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {

    private static CyclicBarrier cBarrier = null;

    public static void main(String[] args) {
        cBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("-----------");
            }
        });

        for (int i = 0; i < 3; i++) {
            Thread party = new PartiesThread();
            party.start();
            if (i == 2) {
                party.interrupt();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cBarrier.reset();

        for (int i = 0; i < 3; i++) {
            Thread party = new PartiesThread();
            party.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正式开会...");
    }

    static class PartiesThread extends Thread {
        @SneakyThrows
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了会议室");
            cBarrier.await();
            System.out.println(Thread.currentThread().getName() + "找到座位");
            cBarrier.await();
            System.out.println(Thread.currentThread().getName() + "准备开会");

        }
    }

}
