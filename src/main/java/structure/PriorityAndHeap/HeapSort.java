package structure.PriorityAndHeap;

import algorithm.Sort.SortTestHelper;

/**
 * @author Fighter.
 */
public class HeapSort {
    private HeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        //Heapify 索引从 0 开始时最后一个非叶子节点索引为：(n-1)-1 / 2
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

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("structure.PriorityAndHeap.HeapSort", arr);
    }
}
