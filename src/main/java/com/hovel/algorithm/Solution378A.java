package com.hovel.algorithm;

public class Solution378A {


    public static void main(String[] args) {
//        int[][] matrix = {{1, 5, 9}, {10, 13, 16}, {12, 15, 18}};
//        System.out.println(kthSmallest(matrix, 8));

//        int[][] matrix1 = {{1, 5}, {12, 30}};
//        System.out.println(kthSmallest(matrix1, 3));

        int[][] matrix2 = {{1, 3, 5}, {6, 7, 12}, {11, 14, 14}};
        System.out.println(kthSmallest(matrix2, 5));
    }


    public static int kthSmallest(int[][] matrix, int k) {
        int length = matrix.length * matrix.length;
        if (k > length || k <= 0) {
            return -1;
        }

        int count = 0;
        int result = 0;

        if (k > length / 2) {
            result = sortLastHalf(matrix, k, matrix.length, count);

        } else {
            result = sortPreHalf(matrix, k, matrix.length, count);
        }
        return result;
    }

    private static int sortLastHalf(int[][] matrix, int k, int length, int count) {
        // 第k小   就是  length-k+1大
        int row = length - 1;
        int col = length - 1;

        int m = col;

        int index = length * length - k + 1;
        while (true) {
            if (k == length) {
                return matrix[row][col];
            }
            System.out.println("i=" + row + ",j=" + col);
            if (matrix[row][col] > matrix[row - 1][m]) {// 本行元素仍大于上一行元素
                int tmp = col;
                col--;
                count++;

                if (count == index) {
                    return matrix[row][tmp];
                }

                if (col < 0) {
                    col = length - 1;
                    row = row - 1;
                }

            } else {
                row--;
                col = length - 1;
                m--;
                if (row <= 0) {
                    return matrix[0][k - count - 1];
                }
            }
        }
    }

    private static int sortPreHalf(int[][] matrix, int k, int length, int count) {

        if (k == 1) {
            return matrix[0][0];
        }
        int i = 0;
        int j = 0;

        int nextRowCol = 0;
        //从前往后
        while (true) {
//            System.out.println("i=" + i + ",j=" + j);
            if (matrix[i][j] <= matrix[i + 1][nextRowCol]) {// 本行元素仍小于下一行元素
                j++;
                count++;

                if (count == k) {
                    return matrix[i][j - 1];
                }

                if (j >= length) {
                    i++;
                    j = 0;
                }
            } else {
                i++;
                j = 0;

                if (i + 1 >= length) {
                    return matrix[length - 1][k - count - 1];
                }
            }
        }
    }
}
