package com.hovel.base.thread;

/**
 * 懒汉式单例模式
 * 线程安全的，比起饿汉式性能资源上更有优势。
 */
public class SingletonDemo2 {

    private SingletonDemo2() {}

    private static SingletonDemo2 singletonDemo = null;

    /**
     * synchronized 只会让一个线程进入创建对象实例。但是
     *  会造成性能问题。
     * @return
     */
    public synchronized static SingletonDemo2 getInstance() {

        if(null == singletonDemo){
            singletonDemo = new SingletonDemo2();
        }
        return singletonDemo;
    }
}
