package algorithm.Sort.QuickSort;

import algorithm.Sort.InsertionSort.InsertionSort;
import algorithm.Sort.SortTestHelper;

/**
 * 若数组重复元素太多，则可能导致 <v 和 >=v 集合不平衡，使得递归树很偏斜，因此这里采用双路快排，等左边 <=v 右边 >=v 时交换
 * <p>
 * 快排优化版本2 处理数组中有大量重复元素，在优化1的基础上双路（设置首尾指针）快排
 * 1. 随机选择 pivot，复杂度期望为 O(N*logN)，此时退化为 O(N*N)，证明见算法导论
 * 2. 小数组转换为插入排序
 * 3. 双路快速排序的 partition
 */
public class QuickSort2Ways {

    private QuickSort2Ways() {
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

    // 双路快速排序的partition
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    //对arr[l...r]部分进行partition，返回p，使得arr[l...p-1] < arr[p] < arr[p+1...r]
    private static int partition(Comparable[] arr, int l, int r) {
        //todo 优化1.随机选择pivot，即随机在 arr[l..r] 选一元素与 arr[l] 互换
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable pivot = arr[l];
        //arr[l+1...i) <= v, arr(j...r] >= v
        //初始化i、j，i、j 均为待考察元素，使得小于等于 pivot 集合和 大于等于 pivot 集合都为空
        int i = l + 1, j = r;
        while (true) {
            /*
            比如数组 1,0,0, ..., 0, 0
            a. 对于 arr[i]<v 和 arr[j]>v 的方式，第一次partition得到的分点是数组中间；
            b. 对于 arr[i]<=v 和 arr[j]>=v 的方式，第一次partition得到的分点是数组的倒数第二个。
            这是因为对于连续出现相等的情况，a方式会交换i和j的值；而b方式则会将连续出现的这些值归为其中一方，使得两棵子树不平衡
            */
            //注意这里的边界条件！不能是pivot.compareTo(arr[i]) >= 0
            while (i <= r && pivot.compareTo(arr[i]) > 0) {
                i++;
            }
            //注意这里的边界条件！不能是pivot.compareTo(arr[j]) <= 0
            while (j >= l + 1 && pivot.compareTo(arr[j]) < 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }

        //退出循环条件是，当i指向第一个大于等于 pivot 元素，j 指向最后一个小于等于 pivot 元素
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
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100);
        SortTestHelper.testSort("algorithm.Sort.QuickSort.QuickSort2Ways", arr);
    }
}


