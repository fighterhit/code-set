package leetcode.middle;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class M16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        //返回和
        int sum = Integer.MAX_VALUE, threshhold = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //因为是返回和，而不是返回之前的具体组合，因此这里可以去掉if判断，因为即使组合重复也可以得到相同的返回结果
//            if (i == 0 || nums[i] != nums[i - 1]) {
                int l = i + 1, r = nums.length - 1;
                while (l < r) {
                    if (Math.abs(nums[i] + nums[l] + nums[r] - target) < threshhold) {
                        sum = nums[i] + nums[l] + nums[r];
                        threshhold = Math.abs(sum - target);
                    }
                    if (nums[i] + nums[l] + nums[r] > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
//            }
        }
        return sum;
    }
}
