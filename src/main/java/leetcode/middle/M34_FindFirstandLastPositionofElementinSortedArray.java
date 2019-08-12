package leetcode.middle;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * P263_NumberOfK
 */
public class M34_FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int firstIndex = binarySearch(nums, target);
        int lastIndex = binarySearch(nums, target + 1) - 1;
        //target元素不存在：1. 二分查找返回索引等于数组长度，即找到边界外 2. 二分查找返回索引在数组长度范围内但元素与指定元素不等
        if (firstIndex == nums.length || nums[firstIndex] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{firstIndex, lastIndex};
    }

    int binarySearch(int[] nums, int target) {
        //注意 h 初始值，此处允许找到边界外，被调用函数以此判断是否存在指定元素！！！
        int l = 0, h = nums.length, m = 0;
        while (l < h) {
            m = l + (h - l >> 1);
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        new M34_FindFirstandLastPositionofElementinSortedArray().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 11);
        new M34_FindFirstandLastPositionofElementinSortedArray().searchRange(new int[]{5, 7, 8, 10}, 6);
    }
}
