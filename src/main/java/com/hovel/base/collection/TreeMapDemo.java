package com.hovel.base.collection;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String, Object> data = new TreeMap<>();
        data.put("A","A");
        data.put("C","C");
        data.put("B","B");

        System.out.println(data);

    }

}
