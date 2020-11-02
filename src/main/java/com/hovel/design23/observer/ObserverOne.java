package com.hovel.design23.observer;

/**
 * 具体抽象类
 */
public class ObserverOne extends AbstractObserver {

    private AbstractSubject subject;

    public ObserverOne() {

    }

    public ObserverOne(AbstractSubject subject) {
        this.subject = subject;
    }

    @Override
    public void response() {
        System.out.println("观察者1做出反应");
    }

    @Override
    public void getStateChangedNotice() {
        System.out.println("观察者1得到状态改变的通知:" + subject.getState());
    }
}
