package com.my.sort;

public class QuckSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(arr, start, end);
//            int pivotIndex = partition2(arr, start, end);
            quickSort(arr, start, pivotIndex-1);
            quickSort(arr, pivotIndex + 1, end);
        }

    }

    // 挖坑法
    private static int partition(int[] arr, int start, int end) {
         int left = start,right = end;
         int index = start;// 坑的位置,初始位置为pivot的位置
         int pivot = arr[start]; // 取第一个元素作为基准元素

        while (left <= right) {// 大循环在左右指针重合或交错时结束
            while (left <= right) {//right指针从右向左进行比较
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while (left <= right) {//left指针从左向右进行比较
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    // 指针交换法
    private static int partition2(int[] arr, int start, int end) {
        int left = start,right = end;
        int pivot = arr[start];
        while (left != right) {
            //控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //控制right指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            //交换left和right指向的元素
            if (left < right) {
                swap(arr, left, right);
            }

        }
        //pivot和指针重合点交换
        swap(arr, left, start);
        return left;

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
