package leetcode.middle;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * <p>
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 参考 M698_PartitiontoKEqualSumSubsets，M698_PartitiontoKEqualSumSubsets 回溯法在该题超时
 * 背包问题
 * 参考 M698_PartitiontoKEqualSumSubsets，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等
 */
public class M416_PartitionEqualSubsetSum {

    //-1 表示未计算，0 表示不能填充，1 表示可以
    int[][] memo;

    //记忆化搜索
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        //nums.length 行，0...sum / 2 + 1 列
        memo = new int[nums.length][sum / 2 + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    //用 nums[0...index] 是否可以填充 sum
    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0 || index < 0) {
            return false;
        }
        if (memo[index][sum] != -1) {
            return memo[index][sum] == 1;
        }
        memo[index][sum] = (tryPartition(nums, index - 1, sum) || tryPartition(nums, index - 1, sum - nums[index])) ? 1 : 0;
        return memo[index][sum] == 1;
    }

    //DP
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int C = sum / 2;
        //nums.length 行，0...sum / 2 + 1 列
        boolean[] memo = new boolean[C + 1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = nums[0] == i;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = C; j >= nums[i]; j--) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }
        return memo[sum / 2];
    }

    /**
     * 定义一个一维的 dp 数组，其中 dp[i] 表示原数组是否可以取出若干个数字，其和为 i。那么我们最后只需要返回 dp[target] 就行了。
     * 初始化 dp[0] 为 true，由于题目中限制了所有数字为正数，那么就不用担心会出现和为 0 或者负数的情况。
     * <p>
     * 关键问题就是要找出状态转移方程了，需要遍历原数组中的数字. 对于遍历到的每个数字 nums[i] 需要更新 dp 数组.
     * 最终目标是想知道 dp[target] 的 boolean 值，就要想办法用数组中的数字去凑出 target，因为都是正数，所以只会越加越大，那么加上 nums[i] 就有可能会组成区间 [nums[i], target] 中的某个值，那么对于这个区间中的任意一个数字j，如果 dp[j - nums[i]] 为true的话，说明现在已经可以组成 j-nums[i] 这个数字了，再加上nums[i]，就可以组成数字j了，那么dp[j]就一定为true。如果之前dp[j]已经为true了，当然还要保持true，所以还要‘或’上自身，于是状态转移方程如下：
     * dp[j] = dp[j] || dp[j - nums[i]]  (nums[i] <= j <= target)
     * <p>
     * 注意：第二个for循环一定要从target遍历到nums[i]，而不能反过来！因为如果我们从nums[i]遍历到target的话，假如nums[i]=1的话，那么[1, target]中所有的dp值都是true，因为dp[0]是true，dp[1]会或上dp[0]，为true，dp[2]会或上dp[1]，为true，依此类推，完全使我们的dp数组失效了，
     * https://www.cnblogs.com/grandyang/p/5951422.html
     * <p>
     * 参考 M518_CoinChange2 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * 参考 M377_CombinationSumIV 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数
     */
    public boolean canPartition3(int[] nums) {
        int n = nums.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            //注意从 target 开始
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}
