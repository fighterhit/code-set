package leetcode.easy;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * 从大到小排序 第 k 大元素
 *
 * @author Fighter.
 */
public class E215_KthLargestElementinanArray {

    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        while (true) {
            int p = partition(nums, l, r);
            if (p == k - 1) {
                return nums[p];
            } else if (p > k - 1) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
    }

    int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && nums[i] > pivot) {
                i++;
            }
            while (j >= l + 1 && nums[j] < pivot) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(nums, i, j);
            i++;
            j--;
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
