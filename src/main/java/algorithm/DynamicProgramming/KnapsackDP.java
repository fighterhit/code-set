package algorithm.DynamicProgramming;

import java.util.List;

/**
 * @author Fighter.
 */
public class KnapsackDP {
    //优化：因为背包问题有两个约束条件，每个状态被两个变量定义，因此开辟的记忆空间为二维数组
    int[][] memo;

    int knapsack(List<Integer> weight, List<Integer> value, int C) {

        assert weight.size() == value.size();
        int n = weight.size();
        if (n == 0) {
            return 0;
        }

        //n 行，0 到 C 共 C+1 列
        memo = new int[n][C + 1];
        //填充
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        //初始化第1行，即只考虑第0个物品
        for (int j = 0; j <= C; j++) {
            memo[0][j] = j >= weight.get(0) ? value.get(0) : 0;
        }

        //从第2行开始填充
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memo[i][j] = memo[i - 1][j];
                if (j >= weight.get(i)) {
                    memo[i][j] = Math.max(memo[i][j], memo[i - 1][j - weight.get(i)] + value.get(i));
                }
            }
        }
        return memo[n - 1][C];
    }
}
