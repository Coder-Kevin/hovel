package com.hovel.base.thread.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierTest2 {

    private static CyclicBarrier cBarrier = null;

    public static void main(String[] args) throws InterruptedException {
        cBarrier = new CyclicBarrier(3, () -> log.info("-----------"));

        for (int i = 0; i < 3; i++) {
            Thread party = new PartiesThread();
            party.start();
        }

        Thread.sleep(3000);
        log.info("正式开会...");
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
