package algorithm.Sort.QuickSort;

import algorithm.Sort.InsertionSort.InsertionSort;
import algorithm.Sort.SortTestHelper;

/**
 * 若数组重复元素太多，则可能导致 <v 和 >=v 集合不平衡，使得递归树很偏斜，因此这里采用双路快排，等左边 <=v 右边 >=v 时交换
 * <p>
 * 快排优化版本3 处理数组中有大量重复元素，在优化2的基础上三路快排，即将排序数组分为<v ,=v, >v 三部分，只对<v 和 >v 部分进行递归排序
 * 1. 随机选择 pivot，复杂度期望为 O(N*logN)，此时退化为 O(N*N)，证明见算法导论
 * 2. 小数组转换为插入排序
 * 3. 将排序数组分为<v ,=v, >v 三部分，只对<v 和 >v 部分进行递归排序
 */
public class QuickSort3Ways {
    private QuickSort3Ways() {
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

        //todo 优化1.随机选择pivot，即随机在 arr[l..r] 选一元素与 arr[l] 互换
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable pivot = arr[l];

        //todo 优化3.三路快排：arr[l+1...lt]<v, arr[lt+1...i)==v, arr[gt...r]>v
        int lt = l, gt = r + 1, i = l + 1;
        while (i < gt) {
            if (arr[i].compareTo(pivot) < 0) {
                swap(arr, lt + 1, i);
                lt++;
                i++;
            } else if (arr[i].compareTo(pivot) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                // arr[i] == v
                i++;
            }
        }
        //注意，最后 pivot 和 <v 最后一个元素互换，最后i == gt，arr[i]>=pivot，不能arr[i]互换，否则可能将大的换到最左边
        swap(arr, l, lt);
        //注意是左半部分是 arr[l...lt-1]
        quickSort(arr, l, lt - 1);
        //注意是右半部分是 arr[gt...r]
        quickSort(arr, gt, r);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("algorithm.Sort.QuickSort.QuickSort3Ways", arr);
    }
}
