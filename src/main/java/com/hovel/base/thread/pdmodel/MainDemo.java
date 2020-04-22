package com.hovel.base.thread.pdmodel;


public class MainDemo {
    public static void main(String[] args) {
        ComputerStore computerStore = new ComputerStore();
        while (computerStore.full()) {
            System.out.println("开始抢购");
            new Thread(new Consumer(computerStore)).start();
        }


        for (int i=0;i<10;i++){
            new Thread(new Producer(computerStore)).start();
        }



//        for (int i=0;i<6;i++){
//            new Thread(new Consumer(computerStore)).start();
//        }

    }
}
