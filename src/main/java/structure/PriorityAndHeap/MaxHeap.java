package structure.PriorityAndHeap;

import structure.Array.GenericArray;

import java.util.Random;

/**
 * @author Fighter Created on 2018/10/5.
 */
public class MaxHeap<E extends Comparable> {

    private GenericArray<E> data;

    public MaxHeap(int capacity) {
        this.data = new GenericArray<>(capacity);
    }

    public MaxHeap() {
        data = new GenericArray<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    public int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index) {
        return 2 * index + 2;
    }

    // 向堆中添加元素
    public void add(E e) throws IllegalAccessException {
        //先添加到数组末尾，再上浮
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //上浮
    private void siftUp(int index) throws IllegalAccessException {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax() throws IllegalAccessException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    //获取堆最大元素，将数组最后一个元素放到根再下沉
    public E extractMax() throws IllegalAccessException {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //下沉
    private void siftDown(int index) throws IllegalAccessException {
        //先判断有没有左孩子
        while (leftChild(index) < size()) {
            int i = leftChild(index);
            if (i + 1 < size() && data.get(i + 1).compareTo(data.get(i)) > 0) {
                i++;
            }
            if (data.get(i).compareTo(data.get(index)) > 0) {
                data.swap(i, index);
                index = i;
            } else {
                break;
            }
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) throws IllegalAccessException {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public MaxHeap(E[] arr) {
        data = new GenericArray<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            try {
                siftDown(i);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 1000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for(int i = 0 ; i < n ; i ++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) throws IllegalAccessException {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else{
            maxHeap = new MaxHeap<>();
            for(int num: testData) {
                maxHeap.add(num);
            }
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++) {
            arr[i] = maxHeap.extractMax();
        }

        for(int i = 1 ; i < testData.length ; i ++) {
            if(arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

}