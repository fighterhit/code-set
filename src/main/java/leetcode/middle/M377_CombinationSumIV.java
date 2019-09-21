package leetcode.middle;

import java.util.Arrays;

/**
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 示例:
 * nums = [1, 2, 3]
 * target = 4
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 因此输出为 7。
 * 进阶：
 * 如果给定的数组中含有负数会怎么样？
 * 问题会产生什么变化？
 * 我们需要在题目中添加什么限制来允许负数的出现？
 * <p>
 * https://leetcode-cn.com/problems/combination-sum-iv/solution/dong-tai-gui-hua-python-dai-ma-by-liweiwei1419/
 * 注意元素不重复，且是组合数，不是指定 target 的 2Sum、3Sum 等，这里每个组合里的元素数不定
 * <p>
 * 参考 M416_PartitionEqualSubsetSum 划分两个和相等的子集
 * 参考 M518_CoinChange2，注意本题将 顺序不同的序列被视作不同的组合，而 M518_CoinChange2 将 (1, 1, 2) 和 (1, 2, 1) 视为一种情况
 */
public class M377_CombinationSumIV {
    /**
     * dp[i]表示目标数为i的解的个数，从1遍历到target，对于每一个数i，遍历nums数组。
     * 如果i>=x, dp[i] += dp[i - x]
     * 比如对于[1,2,3] 4，这个例子，当我们在计算dp[3]的时候，3可以拆分为1+x，而x即为dp[2]，3也可以拆分为2+x，此时x为dp[1]，3同样可以拆为3+x，此时x为dp[0]，把所有的情况加起来就是组成3的所有情况了
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        //注意：在 0 这一点，我们是定义 dp[0] = 1 的，因为它表示如果 nums 里有一个数恰好等于 target，它单独成为 1 种可能。
        dp[0] = 1;
        //先排序，用作循环 i < num 判断
        Arrays.sort(nums);
        /**
         * 对比 M518_CoinChange2，内外循环恰好相反；原因参考图 https://leetcode-cn.com/problems/coin-change-2/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-liweiw/
         * 这道题在每次选择每个数都可以选，这就造成如[1,1...1,1,40,50] 90 : 50+40 和 40+50 前后选择不同，算作不同的情况；
         * 另一种解释：对于每个 target，用所有硬币参与计算，如当遍历到 40 时， dp[90]=40+dp[50]，dp[50]前面已经用所有硬币算出结果了；
         * 而 M518_CoinChange2 将硬币循环放在外层，如当选遍历到 40 时， dp[90]=40+dp[50]，这里的 dp[50] 只是部分值，是只用了前面的循环过的硬币计算得到的
         */
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
