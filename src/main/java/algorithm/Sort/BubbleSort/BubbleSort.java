package algorithm.Sort.BubbleSort;

import algorithm.Sort.SortTestHelper;


public class BubbleSort {
    public BubbleSort() {
    }

    //原始版 607ms
    public static void sort1(Comparable[] arr) {
        int n = arr.length;
        //注意 i 边界
        for (int i = 0; i < n - 1; i++) {
            //注意 j 边界
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    //改进1，578ms 添加标志量，用于标志某一趟排序过程中是否有数据交换
    //如果进行某一趟排序时并没有进行数据交换，则说明数据已经按要求排列好，可立即结束排序，避免不必要的比较过程
    public static void sort2(Comparable[] arr) {
        int n = arr.length;
        boolean flag;
        //注意 i 边界
        for (int i = 0; i < n - 1; i++) {
            flag = false;
            //注意 j 边界
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    //598ms 记下最后一次交换的位置，后边没有交换，必然是有序的，然后下一次排序从第一个比较到上次记录的位置结束即可。
    public static void sort3(Comparable[] arr) {
        int n = arr.length;
        boolean flag = false;
        //记录每次最后一次交换的位置，后面肯定已经有序
        int pos = 0;
        //注意k的初始化
        int k = n - 1;
        for (int i = 0; i < n - 1; i++) {
            flag = false;
            pos = 0;
            //注意这里比较到 k 即可，而不是 n-i-1
            for (int j = 0; j < k; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    pos = j;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
            k = pos;
        }
    }

    //一次排序可以确定两个值，正向扫描找到最大值交换到最后，反向扫描找到最小值交换到最前面 469ms
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int pos = 0, k = n - 1;
        boolean flag = false;

        int low = 0;
//        for (int i = low; i < n - 1; i++) {
        while (true) {
            flag = false;
            pos = 0;
            //正向扫描找最大值
            for (int j = 0; j < k; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    flag = true;
                    pos = j;
                }
            }
            if (!flag) {
                break;
            }
            k = pos;

            //反向扫描
            for (int j = k; j > low; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                    flag = true;
                }
            }
            low++;
            if (!flag) {
                break;
            }
        }
    }

    //思路同上，未优化版
    public static void sort4(Comparable[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int tmp, j;
        while (low < high) {
            //正向冒泡,找到最大者
            for (int i = low; i < high; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                }
            }
            high--;
            //反向冒泡,找到最小者
            for (int i = high; i > low; i--) {
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                }
            }
            low--;
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.BubbleSort.BubbleSort", arr);
    }
}