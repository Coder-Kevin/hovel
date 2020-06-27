package com.hovel.algorithm.sort;

public class SortUtil {

    private SortUtil() {
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = null;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean greater(Comparable m, Comparable n) {
        return m.compareTo(n) > 0;
    }

}
