package com.hovel.algorithm.clps;

import java.util.*;

/**
 * @author Kevin
 * 2.	弹幕是现今网络视频常见的评论方式，能够反映一个视频的火爆程度。假设某个时间一共有 N 条弹幕，
 * 每条弹幕 i 的持续时间为两个整数表示的时间区间(a[i],b[i])，我们定义弹幕数量最多的一个时间段为最精彩时段，求一个视频的最精彩时段。
 */
public class SecondQuestion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter bullet screen numbers:");
        int bulletScreenNumbers = sc.nextInt();

        int[][] timeIntervals = new int[bulletScreenNumbers][2];
        int index = 0;

        Set<Integer> timePoints = new TreeSet<>();
        while (bulletScreenNumbers > 0) {
            bulletScreenNumbers--;
            System.out.println("Please enter time interval:");
            String timeIntervalString = sc.next();

            String[] timeIntervalArray = timeIntervalString.split(",");

            timeIntervals[index][0] = Integer.parseInt(timeIntervalArray[0]);
            timeIntervals[index][1] = Integer.parseInt(timeIntervalArray[1]);
            index++;

            timePoints.add(Integer.parseInt(timeIntervalArray[0]));
            timePoints.add(Integer.parseInt(timeIntervalArray[1]));
        }

        List<Object> timePointList = Arrays.asList(timePoints.toArray());

        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < timeIntervals.length; i++) {
            int start = timeIntervals[i][0];
            int end = timeIntervals[i][1];
            put(start + "-" + end, result);

//            int startIndex = timePointList.indexOf(start);
//            int endIndex = timePointList.indexOf(end);
//
//            int temp = 0;
//            if (endIndex - startIndex > 1) {
//                temp = start;
//                for (int j = startIndex+1; j < endIndex; j++) {
//                    int time = (int) timePointList.get(j);
//                    put(temp + "-" + time, result);
//                    temp = time;
//                }
//            }
        }

        for (Map.Entry<String, Integer> n : result.entrySet()) {
            System.out.println(n.getKey() + "--" + n.getValue());
        }
    }

    public static void put(String key, Map<String, Integer> data) {
        if (data.containsKey(key)) {
            data.put(key, data.get(key) + 1);
        } else {
            data.put(key, 1);
        }
    }

}
