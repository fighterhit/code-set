package leetcode.hard;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * <p>
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * <p>
 * https://www.cnblogs.com/grandyang/p/4298664.html
 */
public class H97_InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        //dp[i][j] 表示 s3[0...i+j-1] 能否由 s1[0...i-1] 和 s2[0...j-1] 组成
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        //s3 为空串时可被 s1 为空串和 s2 为空串交错组成
        dp[0][0] = true;
        //s1 为空时，初始化 dp 第 1 行
        for (int i = 1; i <= len2; i++) {
            //注意 当出现
            if (s2.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[0][i] = true;
        }
        //s2 为空时，初始化 dp 第 1 列
        for (int i = 1; i <= len1; i++) {
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c = s3.charAt(i + j - 1);
                if (c == s1.charAt(i - 1)) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (c == s2.charAt(j - 1)) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        //dp[i][j] 表示 s3[0...i+j-1] 能否由 s1[0...i-1] 和 s2[0...j-1] 组成
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        //s3 为空串时可被 s1 为空串和 s2 为空串交错组成
        dp[0][0] = true;
        //s1 为空时，初始化 dp 第 1 行
        for (int i = 1; i <= len2; i++) {
            //注意：当出现不同字符时候需要终止，后面无需判断
            if (s2.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[0][i] = true;
        }
        //s2 为空时，初始化 dp 第 1 列
        for (int i = 1; i <= len1; i++) {
            //注意：当出现不同字符时候需要终止，后面无需判断
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c = s3.charAt(i + j - 1);
                if ((c == s1.charAt(i - 1) && dp[i - 1][j]) || (c == s2.charAt(j - 1) && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[len1][len2];
    }

    //空间压缩：因为 dp[i][j] 只和左边 dp[i][j-1] 和上边 dp[i-1][j] 有关，因此可以空间压缩
    //dp 数组长度取 s1，s2 中长度小的
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), aim = s3.toCharArray();
        //注意第一个是 >= 不是 > !!! 否则 ch1 和 ch2 长度相等时，longs 和 shorts 都为 ch2
        char[] longs = len1 >= len2 ? ch1 : ch2;
        char[] shorts = len1 < len2 ? ch1 : ch2;
        boolean[] dp = new boolean[shorts.length + 1];
        dp[0] = true;
        for (int i = 1; i <= shorts.length; i++) {
            if (aim[i - 1] != shorts[i - 1]) {
                break;
            }
            dp[i] = true;
        }
        for (int i = 1; i <= longs.length; i++) {
            dp[0] = longs[i - 1] == aim[i - 1] && dp[0];
            for (int j = 1; j <= shorts.length; j++) {
                char c = aim[i + j - 1];
                if ((c == longs[i - 1] && dp[j]) || (c == shorts[j - 1] && dp[j - 1])) {
                    dp[j] = true;
                }
                //注意：除了上面条件外需要对后面的 dp[j] 置 false，否则还是上一次的旧值!!!
                else {
                    dp[j] = false;
                }
            }
        }
        return dp[shorts.length];
    }
}
