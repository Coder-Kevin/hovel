package com.hovel.design23.observer.jdk;

import java.util.Observable;

public class OilFutures extends Observable {

    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        super.setChanged();
        super.notifyObservers(price);
        this.price = price;
    }
}
