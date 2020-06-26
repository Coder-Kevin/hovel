package com.hovel.jvm;

public class Test {

    public static void main(String[] args) {
        System.out.println("Max Memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("Total Memory:" + Runtime.getRuntime().totalMemory());

        byte[] data = new byte[1024 * 1024];

        System.out.println("Max Memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("Total Memory:" + Runtime.getRuntime().totalMemory());

        byte[] data2 = new byte[3 * 1024 * 1024];

        System.out.println("Max Memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("Total Memory:" + Runtime.getRuntime().totalMemory());

        int a = 0x00000000ff980000;

        int b = 0x00000000ffc80000;

//        int c = 0x0000000100000000;

    }
}
