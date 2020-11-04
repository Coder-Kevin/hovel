package com.hovel.design.action.observer;

public class ObserverTwo extends AbstractObserver {

    public ObserverTwo() {

    }

    @Override
    public void response() {
        System.out.println("观察者2做出反应");
    }

    @Override
    public void getStateChangedNotice(int state) {
        System.out.println("观察者2得到状态改变的通知:" + state);
    }
}
