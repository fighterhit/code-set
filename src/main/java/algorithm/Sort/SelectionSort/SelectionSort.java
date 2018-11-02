package algorithm.Sort.SelectionSort;

import algorithm.Sort.SortTestHelper;

public class SelectionSort {
    private SelectionSort() {
    }

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Comparable[] arr, int i, int minIndex) {
        Comparable tmp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = tmp;
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.SelectionSort.SelectionSort", arr);
    }
}
