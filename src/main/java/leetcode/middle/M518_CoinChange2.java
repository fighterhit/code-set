package leetcode.middle;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * <p>
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * 参考 M377_CombinationSumIV，注意和 M377_CombinationSumIV 内外循环不同，因为本题
 * 参考 M322_CoinChange，求凑成总金额所需的最少的硬币个数
 */
public class M518_CoinChange2 {
    public int change(int amount, int[] coins) {
        //不用带判断条件，否则输入 0,[] 不过
        /* if (coins == null || coins.length == 0) {
            return 0;
        }*/
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        /**
         * 对比 M377_CombinationSumIV，内外循环恰好相反；原因参考图 https://leetcode-cn.com/problems/coin-change-2/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-liweiw/
         * M377_CombinationSumIV 在每次计算某个target时，每个数（硬币）都可以选，这就造成如[1,1...1,1,40,50] 90 : 50+40 和 40+50 前后选择不同，算作不同的情况；
         * 另一种解释：对于每个 target，用所有硬币参与计算，如当遍历到 40 时， dp[90] = 40 + dp[50]，dp[50] 前面已经用所有硬币算出结果了，是完整的 dp[50]，包含前后选择顺序不同而得到不同的结果；
         * 而本题将硬币循环放在外层，如当选遍历到 40 时， dp[90] = 40 + dp[50]，这里的 dp[50] 只是部分值，是只用了前面的循环过的硬币计算得到的
         */
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
