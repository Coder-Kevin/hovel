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
    }

    @Test
    public void test2() {
        AbstractSubject subject = new Subject();

        AbstractObserver observer1 = new ObserverOne(subject);
        AbstractObserver observer2 = new ObserverTwo(subject);

        subject.add(observer1);
        subject.add(observer2);

        subject.changeState(2);
    }

}
