package com.hovel.base.thread.singleton;

import net.jcip.annotations.NotThreadSafe;

/**
 * 饿汉式单例模式
 * 利用静态代码块来解决
 */
@NotThreadSafe
public class SingletonDemo4 {

    private SingletonDemo4() {}

    static {
        singletonDemo = new SingletonDemo4();
    }

    // 先执行static  但是后执行下面这句导致为null
    private static SingletonDemo4 singletonDemo = null;

    public static SingletonDemo4 getInstance() {
        return singletonDemo;
    }

    public static void main(String[] args) {
        // null
        System.out.println(SingletonDemo4.getInstance());
        System.out.println(SingletonDemo4.getInstance());
    }

}
