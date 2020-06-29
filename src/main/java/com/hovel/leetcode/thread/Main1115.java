package com.hovel.leetcode.thread;

import lombok.SneakyThrows;

/**
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
public class Main1115 {


    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(3);

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                fooBar.foo(new PrintFoo());
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                fooBar.bar(new PrintBar());
            }
        }).start();


    }

}
