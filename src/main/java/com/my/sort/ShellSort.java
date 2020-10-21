package com.my.sort;

/**
 * 希尔排序是一个优化后的插入排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 3, 2, 0, 6, 7, 1, 8};
        shellSort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }


    //不稳定排序
    private static void shellSort(int[] arr) {
        int d = arr.length;
        while (d > 1) {
            d = d / 2;  // 希尔增量
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int temp = arr[i];
                    int j = i - d;
                    for (; j >= 0 && arr[j] > temp; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = temp;
                }
            }

        }

    }
}
