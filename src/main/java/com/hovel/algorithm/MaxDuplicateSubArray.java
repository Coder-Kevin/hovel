package com.hovel.algorithm;

/**
 * @author lenovo
 * 最长重复子数组
 */
public class MaxDuplicateSubArray {


    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1};

        int[] B = {3, 2, 1, 4, 7};

        MaxDuplicateSubArray maxDuplicateSubArray = new MaxDuplicateSubArray();
//        System.out.println(maxDuplicateSubArray.findLength(A, B));

        int[] A0 = {0, 0, 0, 0, 0};

        int[] B0 = {0, 0, 0, 0, 0};

//        System.out.println(maxDuplicateSubArray.findLength(A0, B0));

        int[] A1 = {1, 0, 0, 0, 0};

        int[] B1 = {0, 0, 0, 0, 1};

//        System.out.println(maxDuplicateSubArray.findLength(A1, B1));

        int[] A2 = {0, 0, 0, 0, 1};

        int[] B2 = {1, 1, 0, 0, 0};

        System.out.println(maxDuplicateSubArray.findLength(A2, B2));
    }

    public int findLength(int[] A, int[] B) {
        int count = 0;
        int result = 0;

        for (int i = 0; i < A.length; i++) {
            int index = i;
            count = 0;
            for (int j = 0; j < B.length; j++) {
                System.out.println("index=" + index + ", j=" + j);
                if (A[index] == B[j]) {
                    count++;
                    index++;
                    if(index >= A.length){
                        // A数组的最后一个元素都是和b相同的
                        return count;
                    }
                } else {
                    int lastIndex = index;
                    lastIndex--;

                    int lastBj = j;
                    lastBj--;

                    if (lastIndex > 0 && lastBj > 0 && A[lastIndex] == B[lastBj]) {
                        if (result < count) {
                            result = count;
                        }
                        break;
                    }
                    continue;
                }
                System.out.println("i=" + i + ",count=" + count + ",index=" + index + ",result=" + result);

            }
            if (count + 1 == B.length) {
                return count;
            }
        }

        return result;
    }


}
