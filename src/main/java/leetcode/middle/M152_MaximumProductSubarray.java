package leetcode.middle;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 参考 E628_MaximumProductofThreeNumbers
 * 面试指南 P403
 */
public class M152_MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], res = nums[0];
        int maxEnd = 0, minEnd = 0;
        for (int i = 1; i < nums.length; i++) {
            maxEnd = max * nums[i];
            minEnd = min * nums[i];
            max = Math.max(Math.max(maxEnd, minEnd), nums[i]);
            min = Math.min(Math.min(maxEnd, minEnd), nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
