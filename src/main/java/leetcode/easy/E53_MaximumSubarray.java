package leetcode.easy;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * 题目要求用 O(N) 和 O(logN) 两种方法实现
 */
public class E53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //当preSum <= 0 时，累加数组元素只会使得到的和更小，此时应该丢弃这部分和
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            max = Math.max(sum, max);
        }
        return max;
    }

    //区间和：注意初始化条件和求和顺序
    public int maxSubArray3(int[] nums) {
        int sum = 0, minSum = 0, maxSub = Integer.MIN_VALUE;
        for (int num : nums) {
            //先求当前数之前的最小和
            minSum = Math.min(minSum, sum);
            sum += num;
            maxSub = Math.max(maxSub, sum - minSum);
        }
        return maxSub;
    }

    //分治法：把数组一分为二，分别找出左边和右边的最大子数组之和，然后还要从中间开始向左右分别扫描，求出的最大值分别和左右两边得出的最大值相比较取最大的那一个
    public int maxSubArray4(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int l, int r) {
        if (l >= r) {
            return nums[l];
        }
        int mid = l + (r - l >> 1);
        int lMax = helper(nums, l, mid - 1);
        int rMax = helper(nums, mid + 1, r);
        int mMax = nums[mid], sum = nums[mid];
        for (int i = mid - 1; i >= l; i--) {
            sum += nums[i];
            mMax = Math.max(mMax, sum);
        }
        sum = mMax;
        for (int i = mid + 1; i <= r; i++) {
            sum += nums[i];
            mMax = Math.max(mMax, sum);
        }
        return Math.max(Math.max(lMax, rMax), mMax);
    }
}
