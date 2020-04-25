package com.hovel.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Kevin
 */
public class RedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        System.out.println("redis 连接成功 " + jedis.ping());

        jedis.set("hello", "1");
        System.out.println(jedis.get("hello"));

        jedis.close();

    }

    public static Jedis jedis = null;

    @Before
    public void init(){
        jedis = new Jedis();
    }

    @After
    public void close(){
        if(jedis != null){
            jedis.close();
        }
    }

    @Test
    public void list(){
        jedis.lpush("mylist", "1");
        jedis.lpush("mylist", "2");
        jedis.lpush("mylist", "3");
        jedis.lpush("mylist", "4");

        List<String> list = jedis.lrange("mylist",0,2);

        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void keys(){
        Set<String> keys = jedis.keys("my*");
        if(keys != null && !keys.isEmpty()) {
            keys.forEach(key -> System.out.println(key));
        }
    }

    @Test
    public void scan() {
        ScanResult<String> scanResult = jedis.scan("0");
        

    }

}
