package NewCoderProblemSet.Other.DJI.Problem1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        int[] res = new int[caseNum];
        for (int i = 0; i < caseNum; i++) {
            //游戏数
            int N = sc.nextInt();
            //总时间：只有 X 天
            int X = sc.nextInt();
            Integer[] weights = new Integer[N];
            Integer[] values = new Integer[N];
            for (int j = 0; j < N; j++) {
                values[j] = sc.nextInt();
                weights[j] = sc.nextInt();
            }
            res[i] = knapsackOptimize(Arrays.asList(weights), Arrays.asList(values), X);
        }
        for (int i = 0; i < caseNum; i++) {
            System.out.println(res[i]);
        }
    }

    static int[][] memo;

    static int knapsackOptimize(List<Integer> weight, List<Integer> value, int C) {
        int n = weight.size();
        if (n == 0) {
            return 0;
        }
        //n 行，0 到 C 共 C+1 列
        memo = new int[2][C + 1];
        //填充
        for (int j = 0; j < memo[0].length; j++) {
            memo[0][j] = -1;
        }

        //初始化第1行，即只考虑第0个物品
        for (int j = 0; j <= C; j++) {
            memo[0][j] = j >= weight.get(0) ? value.get(0) : 0;
        }

        //从第2行开始填充
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memo[i % 2][j] = memo[(i - 1) % 2][j];
                if (j >= weight.get(i)) {
                    memo[i % 2][j] = Math.max(memo[i % 2][j], memo[(i - 1) % 2][j - weight.get(i)] + value.get(i));
                }
            }
        }
        return memo[(n - 1) % 2][C];
    }
}
