package leetcode.easy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * <p>
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * E121_BestTimetoBuyandSellStock 只交易一次 k=1
 * E122_BestTimetoBuyandSellStockII 连续交易求最大利润，可以无限次买入和卖出 k=+infinity（正无穷）
 * H123_BestTimetoBuyandSellStockIII 只能进行两次的股票交易（1 次交易包括 1 次买入和 1 次卖出） k=2 只允许最多两次交易，且这两次交易不能交叉
 * H188_BestTimetoBuyandSellStockIV 只能进行 k 次的股票交易
 * M309_BestTimetoBuyandSellStockwithCooldown 需要冷却期的股票交易 k=+infinity（正无穷）但加了冷冻期
 * M714_BestTimetoBuyandSellStockwithTransactionFee 需要交易费用的股票交易 k=+infinity（正无穷）但加了手续费
 */
public class E121_BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        //注意最大值设为 0 即可，方便应用于 H123_BestTimetoBuyandSellStockIII
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }
}
