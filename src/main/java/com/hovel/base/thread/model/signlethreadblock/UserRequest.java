package com.hovel.base.thread.model.signlethreadblock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRequest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(200);

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            executorService.submit(new UserTask());
        }

        executorService.shutdown();

    }
}
