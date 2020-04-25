package com.hovel.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author Kevin
 */
public class PublishSubDemo {

    public static void main(String[] args) {

        String publishChannel = "mytopic";

        Jedis jedis = new Jedis();
        jedis.publish(publishChannel,"Hello");

//        JedisPubSub jedisPubSub = new JedisSentinelPool(jedis);
//        jedis.subscribe();


    }

}
