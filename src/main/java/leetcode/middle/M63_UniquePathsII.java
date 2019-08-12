package leetcode.middle;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * Note: m and n will be at most 100.
 * Example 1:
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * 参考 M62_UniquePaths 本题加了障碍物，遇到直接跳过即可
 * https://www.cnblogs.com/grandyang/p/4353680.html
 * https://blog.csdn.net/zxzxzx0119/article/details/82429612
 */
public class M63_UniquePathsII {
    //二维数组DP
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //注意第一个位置若为 1 直接返回0
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] != 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //注意初始化，第一个格子
        dp[0][0] = 1;
        //初始化第一行，注意从 1 开始防止越界
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = dp[0][i - 1];
            }
        }
        //初始化第一列，注意从 1 开始防止越界
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 0)
                    continue;
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    //一维数组 DP
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        //注意第一个位置若为 1 直接返回0
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] != 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        //注意初始化
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //注意一维数组遇到障碍物仍然爱需要赋值dp[j] 否则会使用上次的值，二维数组则不用，因为默认初始化为 0
                if (obstacleGrid[i][j] != 0) {
                    dp[j] = 0;
                } else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
