package com.hovel.basetest;

import java.util.function.Supplier;

public class Dog {

    public static Dog create(Supplier<Dog> supplier){
        return supplier.get();
    }

    public static void eat(Dog dog){
        System.out.println(dog.toString() + "eat shit");
    }
}
