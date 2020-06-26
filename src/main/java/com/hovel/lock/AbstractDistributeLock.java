package com.hovel.lock;

public abstract class AbstractDistributeLock implements DistributeLock {

    /**
     * 加锁
     *
     * @return
     */
    abstract boolean lockByRedis(String key, int expire);

    /**
     * 释放锁
     *
     * @return
     */
    abstract boolean unlockByRedis(String key);


}
