package com.hovel.design.action.strategy;

public class Kitchen {

    private CrabCooking crabCooking;

    public CrabCooking getStrategy() {
        return crabCooking;
    }

    public void setStrategy(CrabCooking crabCooking) {
        this.crabCooking = crabCooking;
    }

    public void cookingMethod(){
        crabCooking.cookingMethod();
    }


}
