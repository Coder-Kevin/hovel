package com.hovel.algorithm.sort;

import java.util.Arrays;

public class SortDemo1 {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 1, 7, 4, 9};
        System.out.println(Arrays.toString(arr));
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {        // 相邻元素两两对比
                    int temp = arr[j + 1];        // 元素交换
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}
