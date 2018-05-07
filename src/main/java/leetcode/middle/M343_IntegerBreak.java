package leetcode.middle;

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

    public static void main(String[] args) {
        System.out.println(integerBreak(4));
    }
}
