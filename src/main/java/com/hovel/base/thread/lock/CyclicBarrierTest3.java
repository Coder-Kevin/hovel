package com.hovel.base.thread.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierTest3 {

    private static CyclicBarrier cBarrier = null;

    public static void main(String[] args) {
        cBarrier = new CyclicBarrier(3, () -> log.info("-----------"));

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
            log.error("{}", e);
        }

        cBarrier.reset();

        for (int i = 0; i < 3; i++) {
            Thread party = new PartiesThread();
            party.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }

        System.out.println("正式开会...");
    }

    static class PartiesThread extends Thread {
        @SneakyThrows
        public void run() {
            log.info(Thread.currentThread().getName() + "到了会议室");
            cBarrier.await();
            log.info(Thread.currentThread().getName() + "找到座位");
            cBarrier.await();
            log.info(Thread.currentThread().getName() + "准备开会");

        }
    }

}
