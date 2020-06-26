package com.hovel.base.thread;

import java.util.Random;

/**
 * 应用volatile可见性的例子，常用于判断性的场景。
 * 下面是一个模拟容器启动失败后再度调用的例子。
 */
public class VolatileDemo {

    public static void main(String[] args) {
        SpringContainer springContainer = new SpringContainer();
        new Thread(new SpringRunTask(springContainer)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--启动监控Spring容器是否初始化完成，否则继续初始化--");
        while (!springContainer.successInit) {
            System.out.println("-- 再重新初始化一次，否则将记录错误 --");
            springContainer.init();
        }

        System.out.println("--Spring容器初始化完成，初始化完成关闭当前线程--");
    }

    static class SpringRunTask implements Runnable {
        private SpringContainer springContainer;

        SpringRunTask(SpringContainer springContainer) {
            this.springContainer = springContainer;
        }

        @Override
        public void run() {
            springContainer.init();
        }
    }

    static class SpringContainer {

        public volatile boolean successInit = false;

        public void init() {
            System.out.println("Spring Application Context Init....");
            try {
                Thread.sleep(300);

                /**
                 * 设置1/3的概率失败
                 */
                int r = new Random().nextInt(3);
                if (r == 1) {
                    throw new RuntimeException("初始化异常....");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            successInit = true;
            System.out.println("Completed...");
        }
    }
}
