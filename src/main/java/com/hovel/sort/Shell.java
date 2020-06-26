package com.hovel.sort;

import java.util.Arrays;

/**
 * @author Kevin
 * 希尔排序
 */
public class Shell {

    public static void sort(Comparable[] arr) {
        // 确定增长量h
        int h = 1;

        while (h < arr.length / 2) {
            h = h * 2 + 1;
        }

        // 希尔排序
        while (h >= 1) {
            // 排序
            // 2.1 找到待插入的元素
            for (int i = h; i < arr.length; i++) {
                // 2.2 插入到有序数列中
                for (int j = i; j >= h; j -= h) {
                    // 待插入的元素a[j],比较a[j]和a[j-h]
                    if (SortUtil.greater(arr[j - h], arr[j])) {
                        //交换元素
                        SortUtil.exchange(arr, j - h, j);
                    } else {
                        // 待插入元素已经找到合适位置，结束循环
                        break;
                    }

                }

            }

            // 减小增长量
            h = h / 2;
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {2, 1, 5, 6, 5, 4, 8, 9, 7, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
