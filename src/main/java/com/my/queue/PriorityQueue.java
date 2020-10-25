package com.my.queue;

import java.util.Arrays;

/**
 * 优先级队列
 */
public class PriorityQueue {
    private int[] arr;
    private int size;

    public PriorityQueue(int len) {
        arr = new int[len];
        size = 0;
    }

    public PriorityQueue() {
        this(3);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(4);
        priorityQueue.enQueue(1);
        priorityQueue.enQueue(20);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(9);
        priorityQueue.enQueue(7);
        System.out.println("size:");
        System.out.println(priorityQueue.size());
        System.out.println("start enqueu: ");
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());
        System.out.println(priorityQueue.deQueue());


    }
    private int size() {
        return size;
    }


    public void enQueue(int val) {
        if (size >= arr.length) {
            resize();
        }
        arr[size++] = val; // 加入到数据末尾,然后上调
        upAdjust(arr);

    }

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty!");
        }

        int head = arr[0];
        arr[0] = arr[--size];// 删除最后一个元素代替第一个元素，然后下调
        downAdjust(arr,0, size);
        return head;

    }

    /**
     * 上浮调整
     * @param arr
     */
    public  void upAdjust(int[] arr){
        int childIndex = size -1;
        int parentIndex = (childIndex-1) /2;
        int tmp = arr[childIndex]; // 保存叶子节点的值，用于最后的赋值
        while (childIndex > 0 && tmp > arr[parentIndex]) {
            // 无需真正交互,单向赋值即可
            arr[childIndex] = arr[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1) /2;
        }
        arr[childIndex] = tmp;

    }

    private  void downAdjust(int[] arr, int parentIndex, int length){
        int childIndex = parentIndex * 2 + 1;
        int tmp = arr[parentIndex];
        while (childIndex < length) {
            // 如果有右孩子，
            if (childIndex+1 < length && arr[childIndex] < arr[childIndex + 1]) {
                childIndex++;
            }
            if (tmp >= arr[childIndex]) {
                break;
            }
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        arr[parentIndex] = tmp;

    }

}
