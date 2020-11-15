package com.hovel.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Slf4j
public class RedissonExample {

    private static RedissonClient client;

    @Before
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);
        log.info("redis client start.....");
    }

    @After
    public void close() {
        client.shutdown();
        log.info("redis client shutdown.....");
    }

    @Test
    public void test() {

    }


}
