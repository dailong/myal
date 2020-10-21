package com.my.sort;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 1, 8};
        selectionSort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }


    //不稳定排序
    private static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1;j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            } // 每一轮走完，选出一个最小值进行交换
            swap(arr, i,minIndex);
        }

    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
