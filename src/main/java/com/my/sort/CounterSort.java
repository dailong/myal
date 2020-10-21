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
        int[] sortedArr2 = counterSort2(arr);
        for (int i : sortedArr2) {
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
    private static int[] counterSort2(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        // 得到最大值与最小值，并计算差值d
        int[] sortedArr = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // 找到待排序元素的最大值
            }
            if (arr[i] < max) {
                min = arr[i]; // 找到待排序元素的最小值
            }
        }

        int d = max-min;
        //2.创建统计数组并统计对应元素个数
        int[] tmp = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            tmp[arr[i]-min]++;
        }
        //3.统计数组变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < tmp.length; i++) {
            sum = sum + tmp[i];
            tmp[i] = sum;
        }
        //4.倒序遍历原始序列，从统计数组找到正确位置，输出到结果数组。
        for (int j = arr.length - 1; j >= 0; j--) {
            sortedArr[tmp[arr[j]-min]-1] = arr[j];
            tmp[arr[j]-min]--;

        }

        return sortedArr;

    }
}
