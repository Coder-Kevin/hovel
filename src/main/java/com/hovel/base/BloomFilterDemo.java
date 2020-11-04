package com.hovel.base;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;
import java.util.ArrayList;

/**
 * 布隆过滤器使用
 */
public class BloomFilterDemo {

    private static int size = 1000000;
    /**
     * size 元素个数
     * fpp  错误率
     */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.0001);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("不在过滤器中");
            }
        }

        List<Integer> list = new ArrayList<>(1000);
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        // 有误伤的数量：330  默认是0.3%
        System.out.println("有误伤的数量：" + list.size());
    }
}
