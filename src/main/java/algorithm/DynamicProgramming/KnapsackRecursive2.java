package algorithm.DynamicProgramming;

import java.util.List;

/**
 * 记忆化搜索
 * 背包问题
 *
 * @author Fighter.
 */
public class KnapsackRecursive2 {

    //优化：因为背包问题有两个约束条件，每个状态被两个变量定义，因此开辟的记忆空间为二维数组
    int[][] memo;

    int knapsack(List<Integer> weight, List<Integer> value, int C) {
        int n = weight.size();
        //n 行，0 到 C 共 C+1 列
        memo = new int[n][C + 1];
        //填充
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        return bestValue(weight, value, n - 1, C);
    }

    //用[0...index]的物品填充容量为C的背包的最大价值
    //index 表示考虑到的序列号，以及当前要处理的容量
    private int bestValue(List<Integer> w, List<Integer> v, int index, int C) {
        if (index < 0 || C <= 0) {
            return 0;
        }
        if (memo[index][C] != -1) {
            return memo[index][C];
        }
        //装第index个物品
        int res = bestValue(w, v, index - 1, C);
        res = Math.max(res, v.get(index) + bestValue(w, v, index - 1, C - w.get(index)));
        return memo[index][C] = res;
    }
}
