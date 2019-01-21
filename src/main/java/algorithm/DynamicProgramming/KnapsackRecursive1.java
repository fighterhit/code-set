package algorithm.DynamicProgramming;

import java.util.List;

/**
 * 普通递归
 * 背包问题
 *
 * @author Fighter.
 */
public class KnapsackRecursive1 {
    //递归解法
    int knapsack(List<Integer> weight, List<Integer> value, int C) {
        int n = weight.size();
        return bestValue(weight, value, n - 1, C);
    }

    //用[0...index]的物品填充容量为C的背包的最大价值
    //index 表示考虑到的序列号，以及当前要处理的容量
    private int bestValue(List<Integer> w, List<Integer> v, int index, int C) {
        if (index < 0 || C <= 0) {
            return 0;
        }
        //装第index个物品
        int res = bestValue(w, v, index - 1, C);
        res = Math.max(res, v.get(index) + bestValue(w, v, index - 1, C - v.get(index)));
        return res;
    }
}
