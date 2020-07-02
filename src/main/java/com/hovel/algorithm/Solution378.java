package com.hovel.algorithm;

import com.hovel.algorithm.sort.SortUtil;

import java.util.Arrays;

public class Solution378 {


    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};

//        for (int i = 0;i<3;i++){
//            for (int j=0;j<3;j++){
//                System.out.print(" " + matrix[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println(matrix.length);

        System.out.println(kthSmallest(matrix, 8));

    }


    public static int kthSmallest(int[][] matrix, int k) {
        int length = matrix.length * matrix.length;
        if (k > length) {
            return -1;
        }

        int[] array = new int[length];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                array[index] = matrix[i][j];
                index++;
            }
        }

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {        // 相邻元素两两对比
                    int temp = array[j + 1];        // 元素交换
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

//        System.out.println(Arrays.toString(array));

        return array[k - 1];


    }
}
