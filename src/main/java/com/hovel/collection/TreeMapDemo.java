package com.hovel.collection;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {
        Map<String, Object> data = new TreeMap<>();
        data.put("A", "Hello");
        data.put("C", "Kevin");
        data.put("B", "World");
        data.put("D", "Job");
        data.put("0", "Tim");

        System.out.println(data);

    }
}
