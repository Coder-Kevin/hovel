package com.hovel.base.thread.lock;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
@Slf4j
public class LockConditionExample {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock(); // 此线程加入到AQS的等待队列
            try {
                log.info("wait single"); // 1
                condition.await(); // 调用此方法，线程会从AQS的队列里移除
                log.info("get single"); // 4
            } catch (Exception e) {
                log.error("异常", e);
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                log.info("get lock"); // 2
                condition.signalAll();
                log.info("send single"); // 3
            } catch (Exception e) {
                log.error("异常", e);
            } finally {
                lock.unlock();
            }
        }).start();
    }

}
