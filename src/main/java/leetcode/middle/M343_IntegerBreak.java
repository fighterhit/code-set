package leetcode.middle;

import java.util.Arrays;

/**
 * @author Fighter Created on 2018/5/7.
 */
public class M343_IntegerBreak {

    public static int integerBreak(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        int[] dp = new int[n + 1];

        // 当 n >= 4 时求dp[n]，注意初始化！！！
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        int temp;

        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = dp[j] * dp[i - j];
                if (dp[i] > max) {
                    max = dp[i];
                }
            }
            dp[i] = max;
        }

        temp = dp[n];
        return temp;
    }

    //递归：暴力搜索
    // 将n进行分割(至少分割两部分), 可以获得的最大乘积
    public int integerBreak2(int n) {
        if (n == 1) {
            return 1;
        }
        int res = -1;
        for (int i = 1; i < n; i++) {
            res = max3(res, i * n - i, i * integerBreak2(n - i));
        }
        return res;
    }

    //记忆化搜索
    private int[] memo;

    public int integerBreak3(int n) {
        //1...n
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        breakInteger3(n);
        return memo[n];
    }

    private int breakInteger3(int n) {
        if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = -1;
        //将 n 分为 i 和 n-i
        for (int i = 1; i < n; i++) {
            res = max3(res, i * (n - i), i * breakInteger3(n - i));
        }
        return memo[n] = res;
    }

    //动态规划
    public int integerBreak4(int n) {
        // memo[i] 表示将数字i分割(至少分割成两部分)后得到的最大乘积
        int [] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解memo[i]
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(4));
    }
}
