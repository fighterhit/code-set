package leetcode.middle;

import java.util.Arrays;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * Example:
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * 题目描述：求从矩阵的左上角到右下角的最小路径和，每次只能向右和向下移动
 * http://www.cnblogs.com/grandyang/p/4353255.html
 * 参考 P233_MaxValueOfGifts
 */
public class M64_MinimumPathSum {
    //动态规划：二维
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    //动态规划：一维
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        //注意初始化为int最大值，下面循环就可以统一
        Arrays.fill(dp, Integer.MAX_VALUE);
        //初始化第一个值
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                } else {
                    dp[j] += grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    //与上面方法一样，只是单独先初始化第一行
    public int minPathSum3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        //初始化第一行
        for (int i = 0; i < n; i++) {
            if (i > 0)
                dp[i] = dp[i - 1] + grid[0][i];
            else
                dp[i] = grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                } else {
                    dp[j] += grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    //直接在 grid 数组进行动态规划
    public int minPathSum4(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        //初始化第一行
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}
