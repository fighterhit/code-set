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

    //二元选择排序
    static void selectSortMinMax(int a[], int n) {
        int i, j, max, min, temp;
        for (i = 0; i < n / 2; i++) {
            max = i;
            min = i;
            for (j = i + 1; j < n - i; j++) {
                if (a[j] > a[max]) {
                    max = j;//每趟排序找出最大值
                    continue;
                }
                if (a[j] < a[min]) {
                    min = j;//每趟排序找出最小值
                }
            }
            temp = a[i];
            a[i] = a[min];//找到最小的数据交换
            a[min] = temp;
            if (max == i) {
                //此时 a[i]已经被a[min]替换了
                temp = a[n - i - 1];
                a[n - i - 1] = a[min];
                a[min] = temp;
            } else {
                temp = a[n - i - 1];
                a[n - i - 1] = a[max];//找到最大的数据交换
                a[max] = temp;
            }
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.SelectionSort.SelectionSort", arr);
    }
}
