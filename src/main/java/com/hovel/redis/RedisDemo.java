package com.hovel.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Kevin
 */
@Slf4j
public class RedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        log.info("redis 连接成功 " + jedis.ping());

        jedis.set("hello", "1");
        log.info(jedis.get("hello"));

        jedis.close();
    }

    public static Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis();
    }

    @After
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void list() {
        jedis.lpush("mylist", "1");
        jedis.lpush("mylist", "2");
        jedis.lpush("mylist", "3");
        jedis.lpush("mylist", "4");
        List<String> list = jedis.lrange("mylist", 0, 2);
        log.info(Arrays.toString(list.toArray()));
    }

    @Test
    public void keys() {
        Set<String> keys = jedis.keys("my*");
        if (keys != null && !keys.isEmpty()) {
            keys.forEach(log::info);
        }
    }

    @Test
    public void scan() {
        ScanResult<String> scanResult = jedis.scan("0");
        List<String> list = scanResult.getResult();
        log.info(Arrays.toString(list.toArray()));
    }

    @Test
    public void multi() {
        Transaction transaction = jedis.multi();
        transaction.set("mu", "ha");
        transaction.zadd("mu", 1, "ha");
        List<Object> execResult = transaction.exec();
        log.info(Arrays.toString(execResult.toArray()));

        log.info(jedis.get("mu"));
    }

    @Test
    public void multiNotSafe() {
        Jedis anotherJedis = new Jedis();
        jedis.set("mu", "hello");
        Transaction transaction = jedis.multi();
        transaction.append("mu", "1");
        anotherJedis.append("mu", "hu");
        List<Object> execResult = transaction.exec();
        log.info(Arrays.toString(execResult.toArray()));
        log.info(anotherJedis.get("mu"));
        anotherJedis.close();
    }

    @Test
    public void multiSafe() {
        Jedis anotherJedis = new Jedis();
        Transaction transaction = jedis.multi();
        transaction.set("mu", "hi"); // 存在这一句，结果不会出问题
        transaction.append("mu", "1");
        anotherJedis.append("mu", "hu");
        List<Object> execResult = transaction.exec();
        log.info(Arrays.toString(execResult.toArray()));
        log.info(anotherJedis.get("mu"));
        log.info(jedis.get("mu"));
        anotherJedis.close();
    }

    @Test
    public void multiWatch() {
        Jedis anotherJedis = new Jedis();
        jedis.set("mu", "1");
        // 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。
        jedis.watch("mu");
        Transaction transaction = jedis.multi();
        transaction.append("mu", "1");
        transaction.set("new1", "2");
        anotherJedis.append("mu", "hu");
        List<Object> execResult = transaction.exec();

        if (execResult == null || execResult.isEmpty()) {
            // 乐观锁
        } else {
            log.info(Arrays.toString(execResult.toArray()));
        }
        log.info(jedis.get("mu"));
        anotherJedis.close();
    }

}
