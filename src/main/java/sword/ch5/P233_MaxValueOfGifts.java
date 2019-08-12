package sword.ch5;

public class P233_MaxValueOfGifts {
    public int getMost(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                int left = 0, up = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = Math.max(left, up) + values[i][j];
            }
        }
        return dp[values.length - 1][values[0].length - 1];
    }

    //dp[i][j] 只与 dp[i-1][j]、dp[i][j-1] 有关，因此可只用一维数组
    //    .....
    //....#
    public int getMost2(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }
        int[] dp = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                int left = 0, up = 0;
                if (i > 0) {
                    up = dp[j];
                }
                if (j > 0) {
                    left = dp[j - 1];
                }
                dp[j] = Math.max(left, up) + values[i][j];
            }
        }
        return dp[values.length - 1];
    }
}
