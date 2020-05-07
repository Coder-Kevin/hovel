package com.hovel.base.thread;

public class ThreadStateDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("--" + thread.getState());

        thread.start();

        do {
            System.out.println("--" + thread.getState());
            if (thread.isAlive()) {
                System.out.println("---" + thread.getState());
            }
            if (thread.getState() == Thread.State.TERMINATED) {
                break;
            }

        }while (true);
    }
}
