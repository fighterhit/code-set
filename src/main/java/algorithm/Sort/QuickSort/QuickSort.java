package algorithm.Sort.QuickSort;

import algorithm.Sort.SortTestHelper;

/**
 * 快排初始版本
 * 数组有序情况下，数组最左端pivot最小，递归树可能退化为链表，此时复杂度为O(N*N)
 */
public class QuickSort {

    private QuickSort() {
    }

    public static void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Comparable[] arr, int l, int r) {
        if (l < r) {
            int p = partition(arr, l, r);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, r);
        }
    }

    private static int partition(Comparable[] arr, int l, int r) {
        //取最左边元素为枢纽元素
        Comparable pivot = arr[l];
        //arr[l+1...j] < v;  arr[j+1...i) >= v
        //初始化，两个集合均为空，i 表示待考察元素，j 表示 <v 集合最后一个元素
        int i = l + 1, j = l;
        while (i <= r) {
            if (arr[i].compareTo(pivot) < 0) {
                swap(arr, i, j + 1);
                i++;
                j++;
            } else {
                i++;
            }
        }
        //交换 <v 的最后一个元素和 pivot
        swap(arr, l, j);
        //返回 <v 的最后一个元素的索引，即pivot交换后的索引 j
        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.QuickSort.QuickSort", arr);
    }
}
