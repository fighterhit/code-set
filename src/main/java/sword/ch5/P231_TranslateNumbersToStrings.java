package sword.ch5;

/**
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 */
public class P231_TranslateNumbersToStrings {
    //从前到后
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        //dp[i] 表示前 i 个字符解码方式数量
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.parseInt(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public int numDecodings2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = (s.charAt(n - 1) == '0') ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                continue;
            } else {
                dp[i] = Integer.parseInt(s.substring(i, i + 2)) <= 26 ? dp[i + 1] + dp[i + 2] : dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        new P231_TranslateNumbersToStrings().numDecodings("10");
    }
}
