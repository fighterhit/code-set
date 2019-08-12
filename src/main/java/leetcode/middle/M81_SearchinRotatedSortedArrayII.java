package leetcode.middle;

/**
 * 元素可重复，对比 M33_SearchinRotatedSortedArray
 */
public class M81_SearchinRotatedSortedArrayII {
    /**
     * 先找循环数组最小值，然后确定是在最小值左区间还是右区间
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int l = 0, r = n - 1, m = -1, pivot = -1;
        int[] ints = {-1};
        while (l < r) {
            m = l + (r - l >> 1);
            if (nums[m] == nums[l] && nums[m] == nums[r]) {
                pivot = fincMin(nums, l, r, ints, target);
                break;
            }
            if (nums[m] <= nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (ints[0] != -1) {
            return true;
        }
        if (pivot == -1) {
            pivot = l;
        }
        l = 0;
        r = n - 1;
        if (target <= nums[r]) {
            l = pivot;
        } else {
            r = pivot - 1;
        }
        while (l <= r) {
            m = l + (r - l >> 1);
            if (nums[m] == target) {
                return true;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    int fincMin(int[] arr, int l, int r, int[] ints, int target) {
        int minIndex = l;
        for (int i = l; i <= r; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
            if (arr[i] == target) {
                ints[0] = i;
            }
        }
        return minIndex;
    }

    /**
     * M33_SearchinRotatedSortedArray 不存在相同值，在比较中间值和最右值时就完全符合之前所说的规律：
     * 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的。
     * 而如果可以有重复值，就会出现来面两种情况，[3 1 1] 和 [1 1 3 1]. 对于这两种情况中间值等于最右值时，目标值3既可以在左边又可以在右边，对于这种情况其实处理非常简单，只要把最右值向左一位即可继续循环，如果还相同则继续移，直到移到不同值为止，然后其他部分还采用 Search in Rotated Sorted Array 中的方法
     * https://www.cnblogs.com/grandyang/p/4325840.html
     */
    public boolean search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = -1;
        while (l <= r) {
            mid = l + (r - l >> 1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < nums[r]) {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else if (nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new M81_SearchinRotatedSortedArrayII().search(new int[]{1, 1, 3, 1}, 3);
    }
}
