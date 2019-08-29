package leetcode.middle;

import java.util.Arrays;

/**
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 注意:
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 */
public class M698_PartitiontoKEqualSumSubsets {
    /**
     * 求出数组的所有数字之和sum，首先判断sum是否能整除k，不能整除的话直接返回false。
     * 变量 visited 数组：记录哪些数组已经被选中了，然后调用递归函数，目标是组k个子集合，是的每个子集合之和为target = sum/k。
     * 变量start：表示从数组的某个位置开始查找，curSum为当前子集合之和，在递归函数中，如果k=1，说明此时只需要组一个子集合，那么当前的就是，直接返回true。
     * 变量当前和 curSum 等于 target，再次调用递归，此时传入k-1，start和curSum都重置为0，因为当前又找到了一个和为target的子集合，要开始继续找下一个。否则的话就从start开始遍历数组，如果当前数字已经访问过了则直接跳过，否则标记为已访问。然后调用递归函数，k保持不变，因为还在累加当前的子集合，start传入i+1，curSum传入curSum+nums[i]，因为要累加当前的数字，如果递归函数返回true了，则直接返回true。否则就将当前数字重置为未访问的状态继续遍历
     */
    boolean[] visit;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        //先排序，方便后面剪枝
        Arrays.sort(nums);
        visit = new boolean[nums.length];
        return helper(nums, k, 0, sum / k, 0);
    }

    private boolean helper(int[] nums, int k, int curSum, int target, int start) {
        if (k == 1) {
            return true;
        }
        //当前和大于指定值则直接返回 false，后面无须再找
        if (curSum > target) {
            return false;
        }
        if (curSum == target) {
            return helper(nums, k - 1, 0, target, 0);
        }
        for (int i = start; i < nums.length; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            if (helper(nums, k, curSum + nums[i], target, i + 1)) {
                return true;
            }
            visit[i] = false;
        }
        return false;
    }
}
