package com.hovel.base.thread.singleton;

import net.jcip.annotations.ThreadSafe;

/**
 * 饿汉式单例模式
 * 线程安全的，但是会造成内存浪费，尤其是在没有真正使用到的情况下，性能上也是浪费。
 */
@ThreadSafe
public class SingletonDemo {

    private SingletonDemo () {}

    private static SingletonDemo singletonDemo = new SingletonDemo();

    public static SingletonDemo getInstance() {
        return singletonDemo;
    }

}
