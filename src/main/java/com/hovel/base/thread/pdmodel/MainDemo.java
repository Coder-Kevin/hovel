package com.hovel.base.thread.pdmodel;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainDemo {
    public static void main(String[] args) throws InterruptedException {
        ComputerStore computerStore = new ComputerStore();



        for (int i=0;i<10;i++){
            new Thread(new Producer(computerStore)).start();
        }

        Thread.sleep(1000);

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        while (computerStore.hasProduct()) {
            executorService.submit(new Consumer(computerStore));
        }

        executorService.shutdown();

    }
}
