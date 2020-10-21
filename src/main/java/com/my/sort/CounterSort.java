package com.my.sort;

/**
 * 计数排序
 * 1.当数列最大最小值差距过大时，不适用，
 * 2.当数列元素不是整数，不适用。
 */
public class CounterSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6,4,8,7,9,2,0};
        int[] sortedArr = counterSort(arr);
        for (int i : sortedArr) {
            System.out.print(i+ ",");
        }
    }

    private static int[] counterSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // 找到待排序元素的最大值
            }
        }
        int[] tmp = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            tmp[arr[i]]++;
        }
        int[] sortedArr = new int[arr.length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i]; j++) {
                sortedArr[index++] = i;
            }
        }
        return sortedArr;
    }

    // 优化版, 稳定排序
    private static int[] counterSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // 找到待排序元素的最大值
            }
            if (arr[i] < max) {
                min = arr[i]; // 找到待排序元素的最小值
            }

            int d = max-min;
            int[] tmp = new int[d + 1];
            for (int i = 0; i < arr.length; i++) {
                tmp[arr[i]-min]++;
            }
            for (int i = 1; i < tmp.length; i++) {
                tmp[i] = tmp[i-1] + tmp[i];
            }

            for (int j = arr.length - 1; j >= 0; j--) {

            }

        }


    }
}
