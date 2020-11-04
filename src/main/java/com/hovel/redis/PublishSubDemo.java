package com.hovel.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Kevin
 */
public class PublishSubDemo {

    public static void main(String[] args) {
        String publishChannel = "mytopic";

        Jedis jedis = new Jedis();
        jedis.publish(publishChannel, "Hello");

    }

}
