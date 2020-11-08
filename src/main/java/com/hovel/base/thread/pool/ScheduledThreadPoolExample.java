package com.hovel.base.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledThreadPoolExample {

    public static void main(String[] args) {
        System.out.println("执行的时间大于设定的周期");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        service.scheduleAtFixedRate(() -> {
            try {
                log.info("start task");
                Thread.sleep(8000);
                log.info("done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3000, 5000, TimeUnit.MILLISECONDS);
    }
}
