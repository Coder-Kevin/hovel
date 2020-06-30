package com.hovel.leetcode.thread;

public class PrintBar implements Runnable {


    @Override
    public void run() {
        System.out.print("bar");
    }
}
