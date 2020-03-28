package com.hovel.base.thread;

public class SingletonEnumDemo {
    private SingletonEnumDemo() {}

    public synchronized static SingletonEnumDemo getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonEnumDemo singletonDemo = null;

        // JVM保证该方法只会被调用一次，绝对安全
        Singleton () {
            singletonDemo = new SingletonEnumDemo();
        }

        public SingletonEnumDemo getInstance () {
            return singletonDemo;
        }
    }
}
