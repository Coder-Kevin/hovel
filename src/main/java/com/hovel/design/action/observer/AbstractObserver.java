package com.hovel.design.action.observer;

/**
 * 抽象观察者类
 */
public abstract class AbstractObserver {

    public abstract void response();

    public abstract void getStateChangedNotice(int state);

}
