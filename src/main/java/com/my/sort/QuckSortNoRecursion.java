package com.my.sort;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 非递归快速排序
public class QuckSortNoRecursion {

    public static void main(String[] args) {
        int[] arr = new int[]{6,5,4,3,2,1};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            Stack<Map<String, Integer>> stack = new Stack<>();
            Map<String, Integer> params = new HashMap<>();
            params.put("startIndex", start);
            params.put("endIndex", end);
            stack.push(params);

            while (!stack.isEmpty()) {
                Map<String, Integer> popParam = stack.pop();
                int pivotIndex = partition2(arr, popParam.get("startIndex"), popParam.get("endIndex"));
                if (params.get("startIndex") < pivotIndex - 1) {
                    params = new HashMap<>();
                    params.put("startIndex", popParam.get("startIndex"));
                    params.put("endIndex", pivotIndex - 1);
                    stack.push(params);
                }

                if (pivotIndex +1 < popParam.get("endIndex")) {
                    params = new HashMap<>();
                    params.put("startIndex", pivotIndex + 1);
                    params.put("endIndex",  popParam.get("endIndex"));
                    stack.push(params);
                }

            }
        }

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
