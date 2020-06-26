package com.hovel.lock;

public interface DistributeLock {

    /**
     * 加锁
     *
     * @return
     */
    boolean lock();

    /**
     * 释放锁
     *
     * @return
     */
    boolean unlock();
}
