package leetcode.middle;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * @author Fighter.
 */
public class M238_ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        //res[i] 存储nums[i] 左侧 nums[0...i-1]相乘结果
        for (int i = 0, tmp = 1; i < nums.length; i++) {
            res[i] = tmp;
            tmp = tmp * nums[i];
        }
        //res[i] 存储 nums[0...i-1] 和 nums[i+1...n-1]相乘结果
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            res[i] = res[i] * tmp;
            tmp = tmp * nums[i];
        }
        return res;
    }
}
