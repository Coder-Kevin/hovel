package com.hovel.design23.observer;

import java.util.*;
import java.util.ArrayList;

/**
 * 抽象目标类
 */
public abstract class AbstractSubject {

    protected List<AbstractObserver> observerList = new ArrayList<>();

    protected int state;

    public void add(AbstractObserver observer) {
        observerList.add(observer);
    }

    public void remove(AbstractObserver observer) {
        observerList.remove(observer);
    }

    public abstract void notifyAllObservers();

    public abstract void notifyAllObserversStateChanged();

    public void changeState(int state) {
        System.out.println("数据状态发生改变-------");
        this.state = state;
        notifyAllObserversStateChanged();
    }

}
