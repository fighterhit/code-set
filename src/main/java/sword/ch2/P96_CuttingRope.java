package sword.ch2;

import java.util.Arrays;

/**
 * 减绳子
 * https://leetcode.com/problems/integer-break/description/
 *
 * @author Fighter Created on 2018/5/7.
 */
public class P96_CuttingRope {
    public int integerBreak(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    int[] memo;

    public int integerBreak2(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        integerBreak22(n);
        return memo[n];
    }

    public int integerBreak22(int n) {
        if (n < 2) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            res = max3(res, i * (n - i), i * integerBreak22(n - i));
        }
        memo[n] = res;
        return memo[n];
    }

    //贪心算法
    public int integerBreak3(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        //可能存在整除 3 的，如 12，此时 timesOf2 = 0
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }
}
