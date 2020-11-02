package com.hovel.design23.observer.jdk;

import java.util.Observable;

public class Main {
    public static void main(String[] args) {
        OilFutures oilFutures = new OilFutures();
        Bull bull = new Bull();
        Bear bear = new Bear();

        oilFutures.addObserver(bull);
        oilFutures.addObserver(bear);

        oilFutures.setPrice(10);
        oilFutures.setPrice(-1);

    }

}
