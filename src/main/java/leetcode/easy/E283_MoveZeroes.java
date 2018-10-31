package leetcode.easy;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class E283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        // 双指针法
        // 循环不变式：nums中, [0...k)的元素均为非0元素
        int k = 0;
        // 遍历到第i个元素后,保证[0...i]中所有非0元素
        // 都按照顺序排列在[0...k)中
        // 同时, [k...i] 为 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        while (k < nums.length) {
            nums[k++] = 0;
        }
    }
}
