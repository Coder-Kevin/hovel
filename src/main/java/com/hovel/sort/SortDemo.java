package com.hovel.sort;

import java.util.Arrays;

public class SortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{2,5,1,7,4,9};
        System.out.println(Arrays.toString(arr));
        for (int i=0;i<arr.length;i++) {
            for (int j = i+1;j<arr.length-i;j++) {
                if(arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
    }

}
