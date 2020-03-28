package com.hovel.base.thread.singleton;

import net.jcip.annotations.NotThreadSafe;

/**
 * 懒汉式单例模式
 * 线程不安全的，比起饿汉式性能资源上更有优势。
 */
@NotThreadSafe
public class SingletonDemo1 {

    private SingletonDemo1() {}

    private static SingletonDemo1 singletonDemo = null;

    public static SingletonDemo1 getInstance() {
        /**
         * 线程不安全出现在下面的if代码块
         * Thread_A 和 Thread_B同时到达if的时候，就会出现两个对象实例。
         */
        if(null == singletonDemo){
            singletonDemo = new SingletonDemo1();
        }
        return singletonDemo;
    }
}
