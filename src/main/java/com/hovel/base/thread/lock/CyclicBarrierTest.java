package com.hovel.base.thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static CyclicBarrier cBarrier = null;

    public static void main(String[] args) {
        cBarrier = new CyclicBarrier(5, new Runnable() {

            @Override
            public void run() {
                System.out.println("人到齐了，开会吧");
            }
        });

        for (int i = 0; i < 5; i++) {
            Thread party = new PartiesThread();
            party.start();
//            if(i == 4){
//                party.interrupt();
//            }
        }
    }

    static class PartiesThread extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了会议室");
            try {
                cBarrier.await();

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
