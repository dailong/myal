package com.my.sort;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{6,4,8,7,9,2,0};
        insertSort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i]; // 要插入的元素
            int j = i-1;
            for (;j>=0 && insertVal < arr[j] ; j--) {  // 从要插入的元素位置的前一个元素开始比较
                 arr[j+1] = arr[j];
            }
            arr[j+1] = insertVal;  //由于上面for循环完整后j--了，所以再加上
        }
    }
}
