package com.hovel.base.thread;

/**
 * 执行一个main线程，jvm会启动多少线程
 *
 * Reference Handler 是否是守护线程：true
 * Finalizer 是否是守护线程：true
 * Signal Dispatcher 是否是守护线程：true
 * Attach Listener 是否是守护线程：true
 * main 是否是守护线程：false
 * Monitor Ctrl-Break 是否是守护线程：true
 */
public class HelloWorldThreadNums {


    public static void main(String[] args) {

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        int maxThreads = 0;
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
            maxThreads = threadGroup.activeCount();
        }

        Thread[] threads = new Thread[maxThreads];

        threadGroup.enumerate(threads);

        for (Thread thread : threads) {
            System.out.println(thread.getName() + " 是否是守护线程：" + thread.isDaemon());
        }


    }


}
