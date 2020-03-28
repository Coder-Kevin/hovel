package com.hovel.base.thread.singleton;

import net.jcip.annotations.NotThreadSafe;

/**
 * 懒汉式单例模式
 * 线程不安全的，比起饿汉式性能资源上更有优势。
 */
@NotThreadSafe
public class SingletonDemo3 {

    private SingletonDemo3() {}

    // volatile解决下面的问题，避免指令重排序
    private volatile static SingletonDemo3 singletonDemo = null;

    /**
     * synchronized 下沉到方法内部，这种形式也可以叫做：
     * 双重检测同步机制
     * 这样同样会有问题：造成的原因是指令重排序
     *
     * 创建对象简单过程：
     *  1.分配对象内存空间
     *  2.引用指向刚才分配的内存空间
     *  3.初始化对象
     *
     *  指令重排序会让：2、3进行颠倒
     *
     *
     * @return
     */
    public synchronized static SingletonDemo3 getInstance() {

        if(null == singletonDemo){   // 线程A  如果是132的过程B->3，A在此处就会直接返回未完成初始化的对象实例
            synchronized (SingletonDemo.class) {
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo3(); // 线程B - 1【3】2执行到第三步
                }
            }
        }
        return singletonDemo;
    }
}
