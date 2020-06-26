package com.hovel.algorithm;

public class MaxLengthSubStringDemo {

    public static void main(String[] args) {
//        System.out.println(maxLengthSubString("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println(maxLengthSubString("abca"));
    }

    public static int maxLengthSubString(String str) {
        int[] hash = new int[300];
        int count = 0;

        int i = 0, j = 0;

        while (i < str.length() && j < str.length()) {
            System.out.println("j:" + j + " :" + str.charAt(j) + "-" + (int) str.charAt(j) + "--" + hash[str.charAt(j)]);
            if (hash[str.charAt(j)] == 0) {
                hash[str.charAt(j)] = 1;
                j++;
                count = j - i > count ? j - i : count;
            } else {
                hash[str.charAt(i)] = 0;
                i++;
            }
        }

        return count;
    }


    public static String maxSubString(String str) {


        return "";
    }


}
