package com.my.tree;

/**
 * 二叉堆
 */
public class Heap {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,1,6,5,8,3,0};
        upAdjust(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ,");
        }
        System.out.println();
        System.out.println("build heap");
        int[] arr1 = new int[]{2,4,1,6,5,8,3,0};
        buildHeap(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ,");
        }

    }


    /**
     * 上浮调整
     * @param arr
     */
    public static void upAdjust(int[] arr){
        int childIndex = arr.length-1;
        int parentIndex = (childIndex-1) /2;
        int tmp = arr[childIndex]; // 保存叶子节点的值，用于最后的赋值
        while (childIndex > 0 && tmp < arr[parentIndex]) {
            // 无需真正交互,单向赋值即可
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1) /2;
        }
        arr[childIndex] = tmp;

    }

    private static void downAdjust(int[] arr, int parentIndex, int len){
        int childIndex = 2 * parentIndex + 1;
        int tmp = arr[parentIndex];
        while (childIndex <= len) {
            if (childIndex + 1 < len && arr[childIndex + 1] < arr[childIndex]) {
                childIndex++;
            }
            if (tmp <= arr[childIndex]) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex *2 +1;
        }
        arr[parentIndex] = tmp;

    }

    static void buildHeap(int[] arr) {
        for (int i = (arr.length-2) /2; i >= 0; i--) {
            downAdjust(arr, i, arr.length-1);
        }
    }

}
