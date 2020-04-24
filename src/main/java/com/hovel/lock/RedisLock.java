package com.hovel.lock;

import redis.clients.jedis.Jedis;

public class RedisLock extends AbstractDistributeLock {

    private Jedis jedis;

    public RedisLock(Jedis jedis){
        this.jedis = jedis;
    }


    @Override
    boolean lockByRedis(String key, int expire) {

        long status = jedis.setnx(key, "1");
        jedis.expire(key, (int) expire);
        if (status == 1) {
            return true;
        }

        return false;
    }

    @Override
    boolean unlockByRedis(String key) {
        if(jedis.del(key) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
