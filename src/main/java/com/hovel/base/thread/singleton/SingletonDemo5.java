package com.hovel.base.thread.singleton;

import net.jcip.annotations.ThreadSafe;

/**
 * 饿汉式单例模式
 * 利用静态代码块来解决
 */
@ThreadSafe
public class SingletonDemo5 {

    private SingletonDemo5() {
    }

    private static SingletonDemo5 singletonDemo = null;

    static {
        singletonDemo = new SingletonDemo5();
    }

    public static SingletonDemo5 getInstance() {
        return singletonDemo;
    }

    public static void main(String[] args) {
        System.out.println(SingletonDemo5.getInstance());
        System.out.println(SingletonDemo5.getInstance());
    }

}
