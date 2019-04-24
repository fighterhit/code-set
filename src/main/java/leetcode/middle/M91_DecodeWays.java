package leetcode.middle;

/**
 * 前缀都合法，斐波那契 numDecodings(s) = fib(len(s) + 1)
 * https://www.bilibili.com/video/av42112212?from=search&seid=16243515850943518746
 *
 * @author Fighter.
 */
public class M91_DecodeWays {
    //时间O(n) 空间O(n)
    public int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int sLen = s.length();
        int[] dp = new int[sLen + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= sLen; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[sLen];
    }

    //时间O(n) 空间O(1)
    public int numDecodings2(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        //dp[i-1]
        int w1 = 1;
        //dp[i-2]
        int w2 = 1;
        for (int i = 1; i < s.length(); i++) {
            //当前字符串解码方式
            int w = 0;
            if (s.charAt(i) != '0') {
                w += w1;
            }
            if (Integer.valueOf(s.substring(i - 1, i + 1)) >= 10 && Integer.valueOf(s.substring(i - 1, i + 1)) <= 26) {
                w += w2;
            }
            w2 = w1;
            w1 = w;
        }
        return w1;
    }
}
