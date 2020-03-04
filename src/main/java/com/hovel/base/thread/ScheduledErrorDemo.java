package com.hovel.base.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledErrorDemo {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private static int count = 0;

    public static void main(String[] args){
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            count ++;
            System.out.println(count + "--->" + new Date());
            if(count == 3){
                throw new RuntimeException("任务出错了");
            }
        },2000,5000, TimeUnit.MILLISECONDS);
    }
}
