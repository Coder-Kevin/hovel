package com.hovel.design.action.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 空方
 */
public class Bear implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (Float.valueOf(arg.toString()) > 0) {
            System.out.println("油价上涨，空方伤心");
        } else {
            System.out.println("油价下跌，空方高兴");
        }
    }
}
