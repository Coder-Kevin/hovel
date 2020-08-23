package com.hovel.sevice.impl;

import org.springframework.stereotype.Component;

@Component
public class TestService {

    public static void testStatic() {
        System.out.println("-----static---");
    }

    public void test(){
        System.out.println("-------test-----");
    }

    void testDefault(){
        System.out.println("-------null-----");
    }

    public static final void testFinal(){
        System.out.println("-------final-----");
    }

}
