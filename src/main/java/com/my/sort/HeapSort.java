package com.my.sort;


public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,1,6,5,8,3,0};
        for (int i = (arr.length-2) /2; i >=0; i--) {
            adjustTopHeap(arr,i,arr.length); // 构造大顶堆
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ,");
        }
        for (int i = arr.length - 1; i > 0; i--) { // 循环删除堆顶元素移至尾部，继续调整为大顶堆
            // 交换第一个元素和最后一个元素
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            adjustTopHeap(arr,0,i);//继续调整为大顶堆

        }

        System.out.println("\nAfter Sorted: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ,");
        }


    }

    private static void adjustTopHeap(int[] arr, int parentIndex, int length) {
        int childIndex = parentIndex * 2 + 1;
        int tmp = arr[parentIndex];
        while (childIndex < length) {
            // 如果有右孩子，
            if (childIndex+1 < length && arr[childIndex] < arr[childIndex + 1]) {
                childIndex++;
            }
            if (tmp > arr[childIndex]) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        arr[parentIndex] = tmp;
    }

}

