package com.hovel.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {


    public static void main(String[] args) {
        ConcurrentHashMap<String , Object> data = new ConcurrentHashMap<>();

        data.put("Hello", "Test");

        String s = (String) data.get("Hello");
        System.out.println(s);
    }
}
