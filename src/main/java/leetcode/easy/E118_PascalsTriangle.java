package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * 杨辉三角
 */
public class E118_PascalsTriangle {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i + 1];
            if (i == 0) {
                dp[i][0] = 1;
                continue;
            } else {
                dp[i][0] = dp[i][i] = 1;
            }
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            List<Integer> ls = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                ls.add(dp[i][j]);
            }
            res.add(ls);
        }
        return res;
    }

    public List<List<Integer>> generate2(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        for (int i = 0; i < n; i++) {
            List<Integer> ls = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    ls.add(1);
                } else {
                    int a = res.get(i - 1).get(j - 1);
                    int b = res.get(i - 1).get(j);
                    ls.add(a + b);
                }
            }
            res.add(ls);
        }
        return res;
    }
}
