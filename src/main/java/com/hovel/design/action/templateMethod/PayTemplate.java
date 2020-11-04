package com.hovel.design.action.templateMethod;

public abstract class PayTemplate {

    public void payTemplate() {
        confirm();
        pay();
        receipt();
    }

    public void confirm() {
        System.out.println("客户确认付款");
    }

    public void receipt() {
        System.out.println("出具回执给客户");
    }

    public abstract void pay();


}
