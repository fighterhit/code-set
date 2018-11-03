package algorithm.Sort.MergeSort;

import algorithm.Sort.InsertionSort.InsertionSort;
import algorithm.Sort.SortTestHelper;

import java.util.Arrays;

/**
 * 自顶向下归并排序
 */
public class MergeSort {
    private MergeSort() {
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
//        sortBU(arr);
    }

    public static void sort(Comparable[] arr, int l, int r) {
        //优化：遇到数据量小时，近乎有序概率就大，此时转化为插入排序
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }
        if (l < r) {
            int mid = l + (r - l) / 2;
            sort(arr, l, mid);
            sort(arr, mid + 1, r);
            //优化：若已有序则不需归并，可加如下判断进行优化
            if (arr[mid].compareTo(arr[mid + 1]) > 0)
                merge(arr, l, mid, r);
        }
    }

    /**
     * TODO
     * 自底向上归并排序，没有用数组索引，很适合链表，虽然两重循环复杂度依然为 O(NlogN)
     */
    public static void sortBU(Comparable[] arr) {
        int n = arr.length;
        //归并长度从1开始
        for (int size = 1; size < n; size *= 2) {
            for (int i = 0; i < n - size; i += size + size) {
                //右边部分数组可能越界，要保证 "r" < n，因此取 Math.min(i + size + size - 1, n - 1)
                merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1));
            }
        }
    }

    private static void merge(Comparable[] arr, int l, int mid, int r) {
        //只拷贝处理部分，在下面数组索引注意减去l
        Comparable[] tmp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            //左边已经遍历完
            if (i > mid) {
                //注意减去偏移l
                arr[k] = tmp[j - l];
                j++;
            } else if (j > r) {
                //注意减去偏移l
                arr[k] = tmp[i - l];
                i++;
            } else if (arr[i].compareTo(arr[j]) < 0) {
                //注意减去偏移l
                arr[k] = tmp[i - l];
                i++;
            } else {
                //注意减去偏移l
                arr[k] = tmp[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int N = 1000000;
        // Merge Sort是第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.MergeSort.MergeSort", arr);
//        SortTestHelper.testSort("algorithm.Sort.MergeSort.MergeSort", arr);
    }
}
