package algorithm.Sort.QuickSort;

import algorithm.Sort.InsertionSort.InsertionSort;
import algorithm.Sort.SortTestHelper;

/**
 * 快排优化版本1 处理近乎有序数组
 * 1. 随机选择 pivot，复杂度期望为 O(N*logN)，此时退化为 O(N*N)，证明见算法导论
 * 2. 小数组转换为插入排序
 */
public class QuickSort2 {

    private QuickSort2() {
    }

    public static void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Comparable[] arr, int l, int r) {
        //todo 优化2. 小数组转换为插入排序
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }
        int p = partition(arr, l, r);
        //注意是左半部分是 arr[l...p-1]
        quickSort(arr, l, p - 1);
        //注意是右半部分是 arr[p+1...r]
        quickSort(arr, p + 1, r);

    }

    //对arr[l...r]部分进行partition，返回p，使得arr[l...p-1] < arr[p] < arr[p+1...r]
    private static int partition(Comparable[] arr, int l, int r) {
        //todo 优化1.随机选择pivot，即随机在 arr[l..r] 选一元素与 arr[l] 互换
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

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
        SortTestHelper.testSort("algorithm.Sort.QuickSort.QuickSort2", arr);
    }
}

