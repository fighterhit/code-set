package leetcode.middle;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * 元素不重复，对比 M81_SearchinRotatedSortedArrayII
 * <p>
 * 对于数组 [0 1 2 4 5 6 7] 共有下列七种旋转方法（红色表示中点之前或者之后一定为有序的）：
 * https://www.cnblogs.com/grandyang/p/4325648.html
 * 0　　1　　2　　 4　　5　　6　　7
 * 7　　0　　1　　 2　　4　　5　　6
 * 6　　7　　0　　 1　　2　　4　　5
 * 5　　6　　7　　 0　　1　　2　　4
 * 4　　5　　6　　7　　0　　1　　2
 * 2　　4　　5　　6　　7　　0　　1
 * 1　　2　　4　　5　　6　　7　　0
 * 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的
 * 只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
 */
public class M33_SearchinRotatedSortedArray {
    //先找最小元素下标，然后再找目标元素
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int l = 0, r = n - 1, m = -1;
        while (l < r) {
            m = l + (r - l >> 1);
            if (nums[m] <= nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        //l 是最小元素下标
        if (target <= nums[n - 1]) {
            //后半部分
            r = nums.length - 1;
        } else {
            //后半部分
            r = l - 1;
            l = 0;
        }
        while (l <= r) {
            m = l + (r - l >> 1);
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int l = 0, r = n - 1, m = -1;
        while (l <= r) {
            m = l + (r - l >> 1);
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < nums[r]) {
                if (target > nums[m] && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else {
                if (target >= nums[l] && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }
}
