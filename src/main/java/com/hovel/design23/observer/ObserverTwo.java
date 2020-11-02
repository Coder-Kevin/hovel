package com.hovel.design23.observer;

public class ObserverTwo extends AbstractObserver {

    private AbstractSubject subject;

    public ObserverTwo() {

    }

    public ObserverTwo(AbstractSubject subject) {
        this.subject = subject;
    }

    @Override
    public void response() {
        System.out.println("观察者2做出反应");
    }

    @Override
    public void getStateChangedNotice() {
        System.out.println("观察者2得到状态改变的通知:" + subject.getState());
    }
}
