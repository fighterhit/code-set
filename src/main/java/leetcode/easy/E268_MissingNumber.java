package leetcode.easy;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 * <p>
 * Example 2:
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * <p>
 * H41_FirstMissingPositive: 将 nums[i] 存到下标为 nums[i] - 1 的数组位置上
 * P266_GetMissingNumber: 该题是递增排序数组，可用二分，但本题没有排序
 *
 * 输入数组范围 [0, n], 数组长度为 n
 */
public class E268_MissingNumber {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i] ^ i;
        }
        return res;
    }
}
