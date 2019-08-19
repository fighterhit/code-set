package NewCoderProblemSet.Other;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给你一个高为n ，宽为m列的网格，计算出这个网格中有多少个矩形。
 * http://acm.hdu.edu.cn/showproblem.php?pid=2524
 * https://www.cnblogs.com/TenosDoIt/p/3740141.html
 */
public class NMRectNum {
    /**
     * 动态规划，假设dp[i][j]表示以第 i 行第 j 列的格子为右下角顶点的矩形数目.
     * dp[i][j] = 1 + dp[i-1][j] + dp[i][j-1] – dp[i-1][j-1]
     * 这里的1表示i ,j 位置的格子自身构成1*1的矩形，之所以减去dp[i-1][j-1], 因为dp[i-1][j] 和 dp[i][j-1] 都包含了dp[i-1][j-1]。计算时注意i = 1 和 j = 1的边界条件。最后把所有dp[i][j]加起来就是我们所求的答案。
     */
    static int rectNum(int row, int column) {
        int[][] dp = new int[row + 1][column + 1];

        //注意这里的初始化!!!
        for (int i = 0; i <= row; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= column; i++) {
            dp[0][i] = 1;
        }
        dp[0][0] = 2;

        int res = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                dp[i][j] = 1 + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
                //注意累加
                res += dp[i][j];
            }
        }
        return res;
    }

    //组合问题
    int rectNum2(int row, int column) {
        return (row + 1) * row / 2 * (column + 1) * column / 2;
    }

    /**
     * 百度面试题：给定 m * n 的网格，算出不同面积矩形个数
     * 枚举不同面积矩阵个数来发现规律
     * 1 * 1 矩形：m * n 个
     * 1 * 2 矩形：m * (n - 1) 个
     * 2 * 1 矩形：(m - 1) * n 个
     * ...
     * i * j 矩形：(m - i + 1) * (n - j + 1) 个
     */
    static int rectNum3(int m, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                map.put(i * j, map.getOrDefault(i * j, 0) + (m - i + 1) * (n - j + 1));
            }
        }
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int row = sc.nextInt(), column = sc.nextInt();
            System.out.println(rectNum(row, column));
        }
    }
}
