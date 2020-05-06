package com.hovel.base.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 利用反射获取Unsafe
 */
public class ReflectUnsafeClassDemo {

    static Unsafe unsafe = null;
    static long offset = 0;
    private volatile int state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            offset = unsafe.objectFieldOffset(ReflectUnsafeClassDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReflectUnsafeClassDemo useUnsafeClassDemo = new ReflectUnsafeClassDemo();
        boolean result = unsafe.compareAndSwapInt(useUnsafeClassDemo, offset, 0, 1);
        System.out.println(result);

    }
}
