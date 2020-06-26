package com.hovel.basetest;

public class TryInnerFor {

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            if (i == 5) {
                try {
                    throw new RuntimeException("e");
                } catch (Exception e) {

                }
            } else {
                System.out.print(i + "");
            }

        }
    }
}
