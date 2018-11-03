package algorithm.Sort.InsertionSort;

import algorithm.Sort.SortTestHelper;

public class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            Comparable e = arr[i];
            for (; j > 0 && e.compareTo(arr[j - 1]) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void sort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i < r; i++) {
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
