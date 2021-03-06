package com.hovel.base.thread.pdmodel;

import lombok.SneakyThrows;

import java.util.UUID;

public class Producer implements Runnable {

    private ComputerStore computerStore;

    Producer(ComputerStore computerStore) {
        this.computerStore = computerStore;
    }


    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; true; i++) {
            computerStore.create(i % 2 == 0 ? ComputerTypeEnum.DESKTOP_COMPUTER.getTypeCode() : ComputerTypeEnum.NOTE_BOOK.getTypeCode(),
                    UUID.randomUUID().toString().replaceAll("-", ""),
                    "联想" + i);
        }
    }
}
