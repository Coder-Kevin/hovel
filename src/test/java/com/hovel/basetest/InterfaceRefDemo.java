package com.hovel.basetest;

import java.util.Arrays;
import java.util.List;

public class InterfaceRefDemo {

    public static void main(String[] args){
        Dog d = Dog.create(Dog :: new);
        System.out.println(d);

        List<Dog> dogs = Arrays.asList(d,Dog.create(Dog :: new));

        dogs.forEach(Dog :: eat);

        String code = "40300727135539N";
        System.out.print(code.length());

        if(code.length() != 15 && code.length() != 18){
            System.out.print("不可于15或者18位的其他位数");
        }
    }
}
