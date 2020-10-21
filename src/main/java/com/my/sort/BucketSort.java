package com.my.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 * @author dailong
 * @create 2020-10-21 22:37
 **/

public class BucketSort {
    public static void main(String[] args) {
        double[] arr = new double[]{6,4,8,7,9,2,0};
        double[] sortedArr = bucketSort(arr);
        for (double i : sortedArr) {
            System.out.print(i+ ",");
        }
    }

    private static double[] bucketSort(double[] arr) {
        double min = arr[0],max = arr[0];
        // 1.找到最大和最小值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        double d = max - min;
        // 2.创建桶
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new LinkedList());
        }
        // 3.将元素加入到桶中
        for (int i = 0; i < arr.length; i++) {
            int num = (int)((arr.length - min) * (bucketNum - 1) / d);
            buckets.get(num).add(arr[i]);
        }
        // 4.将每个桶中的数据排序
        double[] sortedArr = new double[arr.length];
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(buckets.get(i));
        }

        //5.保存到结果数组
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Double val : buckets.get(i)) {
                sortedArr[index] = val;
                index++;
            }
        }

        return sortedArr;

    }


}
