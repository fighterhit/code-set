package algorithm.Sort.HeapSort;

import algorithm.Sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author Fighter.
 * 堆排序：升序大顶堆，降序小顶堆
 */
public class HeapSort {
    private HeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        //Heapify 索引从 0 开始时最后一个非叶子节点索引为：(n-1)-1 / 2
        //左右孩子统一用 (孩子索引-1)/2 表示父节点，不能用 (孩子索引-2)/2 表示这样只能用于右孩子算父节点而不能代表左孩子
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }
        for (int i = 1; i < n; i++) {
            swap(arr, 0, n - i);
            shiftDown(arr, n - i, 0);
        }
    }

    private static void shiftDown(Comparable[] arr, int n, int i) {
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {
                j += 1;
            }
            if (arr[i].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr, i, j);
            i = j;
        }
    }

    //从大到小排序
    public static void sort2(Comparable[] arr) {
        int n = arr.length;
        //Heapify 索引从 0 开始时最后一个非叶子节点索引为：(n-1)-1 / 2
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shiftDown2(arr, n, i);
        }
        for (int i = 1; i < n; i++) {
            swap(arr, 0, n - i);
            shiftDown2(arr, n - i, 0);
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void shiftDown2(Comparable[] arr, int n, int i) {
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) < 0) {
                j += 1;
            }
            if (arr[i].compareTo(arr[j]) <= 0) {
                break;
            }
            swap(arr, i, j);
            i = j;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.HeapSort.HeapSort", arr);
    }
}
