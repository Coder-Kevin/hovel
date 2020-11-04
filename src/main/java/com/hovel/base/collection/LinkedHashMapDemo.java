package com.hovel.base.collection;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("A", "Hello");
        data.put("B", "World");
        data.put("C", "Kevin");
        data.put("D", "Job");

        data.put("A", "HelloA");
        System.out.println(data);

        Set<Map.Entry<String, Object>> entrySet = data.entrySet();
        for (Map.Entry entry : entrySet) {

        }


    }
}
