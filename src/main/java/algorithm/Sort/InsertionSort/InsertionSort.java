package algorithm.Sort.InsertionSort;

import algorithm.Sort.SortTestHelper;

public class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            Comparable e = arr[i];
            for (; j > 0 && e.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void shellSort(Comparable[] arr) {
        //减小增量
        for (int d = arr.length / 2; d > 0; d /= 2) {
            shellSort(arr, d);
        }
    }

    public static void shellSort(Comparable[] arr, int d) {
        for (int i = d; i < arr.length; i += d) {
            int j = i;
            Comparable e = arr[i];
            for (; j - d >= 0 && e.compareTo(arr[j - d]) < 0; j -= d) {
                arr[j] = arr[j - d];
            }
            arr[j] = e;
        }
    }

    public static void sort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l && e.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.InsertionSort.InsertionSort", arr);
    }
}
