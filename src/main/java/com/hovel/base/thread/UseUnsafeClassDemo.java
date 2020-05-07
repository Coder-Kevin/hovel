package com.hovel.base.thread;

import sun.misc.Unsafe;

/**
 * 只有Bootstrap类加载器才可以获取Unsafe
 */
public class UseUnsafeClassDemo {

    static Unsafe unsafe = Unsafe.getUnsafe();
    private static long offset;
    private static volatile int state = 0;

    static {
        try {
            offset = unsafe.objectFieldOffset(UseUnsafeClassDemo.class.getField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UseUnsafeClassDemo useUnsafeClassDemo = new UseUnsafeClassDemo();
        boolean result = unsafe.compareAndSwapInt(useUnsafeClassDemo, offset, state, 1);
        System.out.println(result);

    }
}
