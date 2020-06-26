package com.hovel.base.thread.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ThreadSafe
@Slf4j
public class ReadWriteLockExample {

    private final Map<String, Data> dataMap = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public static void main(String[] args) {

        ReadWriteLockExample example = new ReadWriteLockExample();
        example.put("1", new Data());
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!example.getAllKeys().isEmpty()) {
                    log.info("{}", example.getAllKeys());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                example.put("2", new Data());
            }
        }).start();
    }

    public Data get(String key) {
        readLock.lock();
        try {
            return dataMap.get(key);
        } finally {
            readLock.unlock();
        }

    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return dataMap.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data data) {
        /**
         * 如果读锁不释放，写锁会一直等待
         */
        writeLock.lock();
        try {
            log.info("wirte : {}, {}", key, data);
            return dataMap.put(key, data);
        } finally {
            writeLock.unlock();
        }
    }

    static class Data {

    }
}
