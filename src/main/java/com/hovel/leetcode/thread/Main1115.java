package com.hovel.leetcode.thread;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 */
public class Main1115 {

    @Test
    public void semphore() throws InterruptedException {
        FooBar fooBar = new FooBar(5);

        new Thread(() -> {
            try {
                fooBar.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Test
    public void waitAndNotify(){
        FooBar2 fooBar2 = new FooBar2(5);

        new Thread(() -> {
            try {
                fooBar2.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar2.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
