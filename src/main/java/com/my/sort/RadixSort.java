package com.my.sort;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        String[] arr = new String[]{"xyz","bd","ef","acd","abc"};
        String[] arrResult = radixSort(arr,3);
        for (String i : arrResult) {
            System.out.print(i + ", ");
        }
    }

    // 稳定排序
    private static String[] radixSort(String[] arr,int maxLenth) {

        String[] sortedArr = new String[arr.length];
        for (int k = maxLenth - 1; k >= 0; k--) {
            int[] countArr = new int[128];
            for (int i = 0; i <arr.length; i++) {
                int ch = getCharByIndex(arr[i], k);
                countArr[ch]++;
            }
            // 变形计数数组，每一个等于前面元素的计数之和
            for (int i = 1; i < countArr.length; i++) {
                countArr[i] = countArr[i-1] + countArr[i];
            }
            // 生成结果数组
            for (int i = arr.length-1; i >=0; i--) {
                int ch = getCharByIndex(arr[i], k);
                sortedArr[countArr[ch]-1] = arr[i];
                countArr[ch]--;
            }
            arr = sortedArr.clone(); // 当前结果作为下一次排序的数组,这个结果必须是稳定排序
        }

        return sortedArr;
    }

    private static int getCharByIndex(String s, int i) {
        if (s.length()-1 < i) {
            return 0;
        }
        return s.charAt(i);
    }
}
