package com.hovel.design23.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 多方
 */
public class Bull implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (Float.valueOf(arg.toString()) > 0) {
            System.out.println("油价上涨，多方高兴");
        } else {
            System.out.println("油价下跌，多方伤心");
        }
    }
}
