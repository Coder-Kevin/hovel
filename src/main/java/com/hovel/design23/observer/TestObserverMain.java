package com.hovel.design23.observer;

import org.junit.Test;

public class TestObserverMain {

    @Test
    public void test1() {
        AbstractObserver observer1 = new ObserverOne();
        AbstractObserver observer2 = new ObserverTwo();

        AbstractSubject subject = new Subject();
        subject.add(observer1);
        subject.add(observer2);

        subject.notifyAllObservers();

        subject.changeState(2);
    }

}
