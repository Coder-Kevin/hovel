package com.hovel.redis;

import redis.clients.jedis.Jedis;

public class RedisDemo {


    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        jedis.set("hello", "1");

        System.out.println(jedis.get("hello"));

    }

}
