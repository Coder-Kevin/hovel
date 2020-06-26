package com.hovel.base.ref;

public class RefenceDemo {

    public static void main(String[] args) {
        int x = 8, y = 9;
        swap(x, y);
        System.out.println("主方法内：x=" + x);
        System.out.println("主方法内：y=" + y);
    }

    public static void swap(int x, int y) {
        int temp = 0;
        temp = x;
        y = x;

        System.out.println("方法内：x=" + x);
        System.out.println("方法内：y=" + y);
    }

}
