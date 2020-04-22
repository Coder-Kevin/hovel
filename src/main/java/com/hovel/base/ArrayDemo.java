package com.hovel.base;

import java.util.Arrays;

public class ArrayDemo {

    public static final String[] arrays = {"A","B","C"};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arrays));

        arrays[1] = "D";
        System.out.println(Arrays.toString(arrays));
    }
}
