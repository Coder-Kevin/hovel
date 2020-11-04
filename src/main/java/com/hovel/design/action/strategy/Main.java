package com.hovel.design.action.strategy;

public class Main {

    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();
        kitchen.setStrategy(new BraisedCrabs());
        kitchen.cookingMethod();

        kitchen.setStrategy(new SteamedCrabs());
        kitchen.cookingMethod();
    }

}
