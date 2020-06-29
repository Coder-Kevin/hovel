package com.hovel.leetcode.thread;

public class PrintFoo implements Runnable {
    @Override
    public void run() {
        System.out.print("foo");
    }
}
