package leetcode.middle;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * <p>
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 * <p>
 * https://www.cnblogs.com/grandyang/p/4353555.html
 * https://blog.csdn.net/linhuanmars/article/details/22126357
 */
public class M62_UniquePaths {

    //动态规划：二维数组
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //注意初始化，第一行
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            //初始化，第一列
            dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //动态规划：一维数组
    //因为 dp[i][j] 只和上、左边元素相关，dp[i]每次都保存的上次同列的值，相当于二维数组上面的值，因此只用一维即可，长度为列数
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        //初始化第一行
        Arrays.fill(dp, 1);
        //更新每行
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    /**
     * 组合问题 https://leetcode.com/problems/unique-paths/discuss/22981/My-AC-solution-using-formula
     * Combination(N, k) = n! / (k!(n - k)!)
     * reduce the numerator and denominator and get
     * C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
     */
    public int uniquePaths3(int m, int n) {
        int N = m + n - 2;
        int k = m - 1;
        double res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (N - k + i) / i;
        }
        return (int) res;
    }

    //递归思想，会超时
    int m, n;

    public int uniquePaths4(int m, int n) {
        this.m = m;
        this.n = n;
        return helper(1, 1);
    }

    int helper(int row, int col) {
        //当行数、列数都达到要求则返回1
        if (row == m && n == col) {
            return 1;
        }
        if (row > m || col > n) {
            return 0;
        }
        return helper(row + 1, col) + helper(row, col + 1);
    }

    public static void main(String[] args) {
        new M62_UniquePaths().uniquePaths(36, 7);
    }
}
