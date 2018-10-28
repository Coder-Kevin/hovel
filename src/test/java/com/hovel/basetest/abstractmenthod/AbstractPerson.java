package com.hovel.basetest.abstractmenthod;

public abstract class AbstractPerson implements Person{

    @Override
    public void say() {
        throw new RuntimeException("say something found error,please say again");
    }
}
