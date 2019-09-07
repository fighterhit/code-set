package leetcode.middle;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class M322_CoinChange {
    /**
     * 1， 5， 11，我们要凑出15的时候， 贪心策略就会出错：
     * 15 = 11 * 1 + 1 * 4 (贪心策略）
     * 15 = 5 * 3（正确策略）
     * 用f(n)来表示“凑出n所需的最少钞票数量”。
     * 现在w=15的时候，我们该取那种钞票呢？当然是各种方案中，cost值最低的那一个
     * - 取11：　cost=f(4)+1=4+1=5
     * - 取5： 　 cost = f(10) + 1 = 2 + 1 = 3
     * - 取1： 　cost = f(14) + 1 = 4 + 1 = 5
     * 显而易见，cost值最低的是取5的方案。我们通过上面三个式子，做出了正确的决策!
     * 这给了我们一个至关重要的启示—— 只与 相关；更确切地说： f(n) 只与 f(n-1),f(n-5),f(n-11) 相关；更确切地说：
     * f(n) = min{f(n-1),f(n-5),f(n-11)}+1
     * https://leetcode-cn.com/problems/coin-change/solution/dong-tai-gui-hua-suan-fa-si-xiang-by-hikes/
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        int cost;
        for (int i = 1; i <= amount; i++) {
            cost = Integer.MAX_VALUE;
            for (int money : coins) {
                if (i - money >= 0 && dp[i - money] != Integer.MAX_VALUE) {
                    cost = Math.min(cost, dp[i - money] + 1);
                }
            }
            //注意：
            //若所有面额都大于当前值 i 或所有 i - money 都为 Integer.MAX_VALUE ，则dp[i] = Integer.MAX_VALUE
            dp[i] = cost;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 完全背包：
     * coins 的值可以作为占用容量，价值是 1. 如题可以的得到：
     * v: 1, 1, 1
     * w: 1, 2, 5
     * 总背包容量是amount = 11 ;
     * 问题转换为填满背包时 总价值最小（经典背包问题时最大）。
     * 详细过程参照 https://www.imooc.com/article/286140
     * 唯一的区别在于选取 out or in 时 选择价值最小的策略。
     * https://leetcode-cn.com/problems/coin-change/solution/wan-quan-bei-bao-by-rocky-12/
     * <p>
     * 给一些面额的硬币，要求用这些硬币来组成给定面额的钱数，并且使得硬币数量最少。硬币可以重复使用。
     * - 物品：硬币
     * - 物品大小：面额
     * - 物品价值：数量
     * 因为硬币可以重复使用，因此这是一个完全背包问题。完全背包只需要将 0-1 背包的逆序遍历 dp 数组改为正序遍历即可。
     * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92.md#4-%E6%89%BE%E9%9B%B6%E9%92%B1%E7%9A%84%E6%9C%80%E5%B0%91%E7%A1%AC%E5%B8%81%E6%95%B0
     * https://leetcode-cn.com/problems/coin-change/solution/dong-tai-gui-hua-jing-dian-de-wan-quan-bei-bao-wen/
     */
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                //如果需要组成的金额正好和某个硬币的面额相等
                if (i == coin) {
                    dp[i] = 1;
                } else {
                    //只有能凑成dp[i - coin]才能凑成dp[i]
                    if (dp[i - coin] != 0) {
                        //若 dp[i] 仍为默认的 0，那么此时即首次遇到能凑出 dp[i] 的硬币 coin，故将 dp[i] 初始化为 dp[i-coin]+1
                        if (dp[i] == 0) {
                            dp[i] = dp[i - coin] + 1;
                        } else {
                            //若 dp[i] 有值，那么取 dp[i-coin]+1 和 dp[i] 的较小值
                            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                        }
                    }
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];

        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
