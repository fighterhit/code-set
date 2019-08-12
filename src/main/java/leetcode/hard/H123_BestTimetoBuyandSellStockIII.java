package leetcode.hard;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 只能交易两次，区别 E121_BestTimetoBuyandSellStock, E122_BestTimetoBuyandSellStockII
 * <p>
 * 从左往右、从右往左分别扫一遍求最大利润
 * https://algorithm.yuanbin.me/zh-hans/dynamic_programming/best_time_to_buy_and_sell_stock_iii.html
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
 */
public class H123_BestTimetoBuyandSellStockIII {
    /**
     * 首先将 [1,n] 拆分为 [1,i] 和 [i+1,n], 参考卖股票系列的第一题计算各自区间内的最大利润即可。
     * [1,i] 区间的最大利润很好算，但是如何计算 [i+1,n] 区间的最大利润值呢？难道需要重复 n 次才能得到？
     * 注意到区间的右侧 n 是个不变值，我们从 [1, i] 计算最大利润是更新波谷的值，那么我们可否逆序计算最大利润呢？这时候就需要更新记录波峰的值了。
     * 即正向扫描更新波谷，逆向扫描更新波峰值
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[] profitFront = new int[n], profitBack = new int[n];
        //正向扫描
        for (int i = 1, min = prices[0]; i < n; i++) {
            profitFront[i] = Math.max(profitFront[i - 1], prices[i] - min);
            //更新波谷
            min = Math.min(prices[i], min);
        }
        //逆向扫描
        for (int i = n - 2, max = prices[n - 1]; i >= 0; i--) {
            profitBack[i] = Math.max(profitBack[i + 1], max - prices[i]);
            //更新波峰
            max = Math.max(prices[i], max);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, profitFront[i] + profitBack[i]);
        }
        return res;
    }

    public int maxProfit2(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int curPrice : prices) {
            if (firstBuy < -curPrice) {
                firstBuy = -curPrice;
            }
            if (firstSell < firstBuy + curPrice) {
                firstSell = firstBuy + curPrice;
            }
            if (secondBuy < firstSell - curPrice) {
                secondBuy = firstSell - curPrice;
            }
            if (secondSell < secondBuy + curPrice) {
                secondSell = secondBuy + curPrice;
            }
        }
        return secondSell;
    }

    /**
     * 最多允许两次不相交的交易，也就意味着这两次交易间存在某一分界线，考虑到可只交易一次，也可交易零次，故分界线的变化范围为第一天至最后一天，只需考虑分界线两边各自的最大利润，最后选出利润和最大的即可。
     */
    public int maxProfit3(int[] prices) {
        int max = 0, leftMax, rightMax;
        for (int i = 0; i < prices.length; i++) {
            leftMax = helper(prices, 0, i);
            rightMax = helper(prices, i + 1, prices.length - 1);
            max = Math.max(leftMax + rightMax, max);
        }
        return max;
    }

    int helper(int[] arr, int l, int r) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            max = Math.max(max, arr[i] - min);
            min = Math.min(min, arr[i]);
        }
        return max;
    }

    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max_k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }

    public static void main(String[] args) {
        //new H123_BestTimetoBuyandSellStockIII().maxProfit2(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        new H123_BestTimetoBuyandSellStockIII().maxProfit3(new int[]{1, 2});
    }
}
