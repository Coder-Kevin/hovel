package com.hovel.base.thread.pdmodel;

import lombok.SneakyThrows;

import java.util.Random;

public class Consumer implements Runnable {

    private ComputerStore computerStore;

    Consumer(ComputerStore computerStore) {
        this.computerStore = computerStore;
    }


    @SneakyThrows
    @Override
    public void run() {
        for (; true; ) {
            Thread.sleep(new Random().nextInt(2) * 100);
            computerStore.consume();
        }
    }
}
