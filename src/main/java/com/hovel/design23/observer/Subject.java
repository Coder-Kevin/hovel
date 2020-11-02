package com.hovel.design23.observer;

/**
 * 具体目标类
 */
public class Subject extends AbstractSubject {

    @Override
    public void notifyAllObservers() {
        for (AbstractObserver observer : observerList) {
            observer.response();
        }
    }

    @Override
    public void notifyAllObserversStateChanged() {
        System.out.println("开始通知所有观察者-----");
        for (AbstractObserver observer : observerList) {
            observer.getStateChangedNotice();
        }
    }
}
