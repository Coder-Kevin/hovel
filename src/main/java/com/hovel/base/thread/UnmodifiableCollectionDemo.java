package com.hovel.base.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象 集合示例
 */
public class UnmodifiableCollectionDemo {

    public static Map<String, Object> map = new HashMap<>();

    static {
        map.put("1", "2");
        map.put("2", "2");
        // 重新map所有可操作方法，直接抛错实现
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        System.out.println(map);

        /*
            Exception in thread "main" java.lang.UnsupportedOperationException
                at java.util.Collections$UnmodifiableMap.put(Collections.java:1457)
                at com.hovel.base.thread.UnmodifiableCollectionDemo.main(UnmodifiableCollectionDemo.java:20)
         */
        map.put("1", "3");
    }
}
