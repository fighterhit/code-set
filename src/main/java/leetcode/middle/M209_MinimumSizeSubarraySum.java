package leetcode.middle;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * Example:
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */
public class M209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int res = nums.length + 1;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l++];
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
   /* public int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length + 1;
        //循环内先得到新窗口，在判断是否大于阈值
        while (l < nums.length) {
            //小了r往后移
            if (r + 1 < nums.length && sum < s) {
                r++;
                sum += nums[r];
            }
            //大了l往后移
            else {
                sum -= nums[l];
                l++;
            }
            //每次循环得到一个新的窗口
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
        }
        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }*/
}
