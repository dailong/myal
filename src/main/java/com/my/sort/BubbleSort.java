package com.my.sort;


public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 1, 8};
//        bubbleSort(arr);
//        bubbleSort2(arr);
        bubbleSort3(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false; // 如果存在交换，说明还未排序完

                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    private static void bubbleSort2(int[] arr) {
        int lastSwapBorder = 0; // 每一轮最后一次交换的边界点，如果后面数据已经有序可以不用重复排序,主要应用于大多数据已经有序
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false;
                    lastSwapBorder = j;

                }
            }
            sortBorder = lastSwapBorder;
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序
     * 第一个循环是从左住右比较交换
     * 第二个循环是从右住左比较交换
     * 适用于大部分元素已经有序
     *
     * @param arr
     */
    private static void bubbleSort3(int[] arr) {
        int lastRightSwapBorder = 0;
        int lastLeftSwapBorder = 0;

        int sortLeftBorder = 0;
        int sortRightBorder = arr.length - 1;

        for (int i = 0; i < arr.length / 2; i++) {

            // 奇数趟排序
            boolean isSorted = true;
            for (int j = sortLeftBorder; j < sortRightBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false;
                    lastRightSwapBorder = j;

                }
            }
            sortRightBorder = lastRightSwapBorder;
            if (isSorted) {
                break;
            }

            // 偶数趟排序
            isSorted = true;
            for (int j = sortRightBorder; j > sortLeftBorder; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    isSorted = false;
                    lastLeftSwapBorder = j;

                }
            }
            sortLeftBorder = lastLeftSwapBorder;
            if (isSorted) {
                break;
            }


        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
