package com.my.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 4, 8, 7, 9, 2, 0};

        mergeSort(arr,0, arr.length-1);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }
    // 分组
    private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }

    }

    // 合并
    private static void merge(int[] arr, int start, int mid, int end) {
        int[] tempArr = new int[end - start + 1];
        int p1 = start, p2 = mid + 1, p = 0;
        while (p1 <= mid && p2 <= end) {
            if (arr[p1] <= arr[p2]) {
                tempArr[p++] = arr[p1++];
            } else {
                tempArr[p++] = arr[p2++];
            }
        }

        while (p1 <= mid) {
            tempArr[p++] = arr[p1++];
        }
        while (p2 <= end) {
            tempArr[p++] = arr[p2++];
        }

        for (int i = 0; i < tempArr.length; i++) {
            arr[start + i] = tempArr[i];
        }

    }




}
