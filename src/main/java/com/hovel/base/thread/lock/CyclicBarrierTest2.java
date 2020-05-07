package com.hovel.base.thread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    private static CyclicBarrier cBarrier = null;

    public static void main(String[] args) throws InterruptedException {
        cBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("-----------");
            }
        });

        for(int i=0;i<3;i++){
            Thread party = new PartiesThread();
            party.start();
        }

        Thread.sleep(3000);
        System.out.println("正式开会...");
    }

    static class PartiesThread extends Thread{
        @SneakyThrows
        public void run(){
            System.out.println(Thread.currentThread().getName()+"到了会议室");
            cBarrier.await();
            System.out.println(Thread.currentThread().getName() + "找到座位");
            cBarrier.await();
            System.out.println(Thread.currentThread().getName() + "准备开会");
        }
    }

}
