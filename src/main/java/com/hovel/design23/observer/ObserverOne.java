package com.hovel.design23.observer;

/**
 * 具体抽象类
 */
public class ObserverOne extends AbstractObserver {

    public ObserverOne() {

    }

    @Override
    public void response() {
        System.out.println("观察者1做出反应");
    }

    @Override
    public void getStateChangedNotice(int state) {
        System.out.println("观察者1得到状态改变的通知:" + state);
    }
}
