package leetcode.hard;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * <p>
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * <p>
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * <p>
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * <p>
 * https://www.bilibili.com/video/av15945552?from=search&seid=8534796919882059910
 * https://www.cnblogs.com/grandyang/p/4461713.html
 * <p>
 * 可以用DP来解，定义一个二维的DP数组，其中dp[i][j]表示s[0,i)和p[0,j)是否match
 * https://www.cnblogs.com/grandyang/p/4461713.html
 * https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
 * https://www.nowcoder.com/questionTerminal/45327ae22b7b413ea21df13ee7d6429c
 * 参考 P124_RegularExpressionsMatching
 * 类比 H44_WildcardMatching
 */
public class H10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        //dp[i][j]表示s[0,i)和p[0,j)是否match
        //注意do数组大小: dp[m + 1][n + 1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        //s, p 都为空
        dp[0][0] = true;

        //初始化：s为空，p不为空时，p只能为类似 "a*" "a*a*a*"
        //注意 i 初始值从 1 开始：因为dp[i][j]表示s[0,i)和p[0,j)是否match
        // i=j=0 时即 dp[0][0] 表示s、p都为空串，上面已初始化为true，因此真正字符串应从 i = 1 开始，字符串每一位都是 s.charAt(i-1) 或者 p.charAt(j-1)
        for (int i = 1; i <= n; i++) {
            //p当前字符为'*'，本题保证 * 前必须有字符搭配即不能单独匹配，注意和 H44_WildcardMatching 区别，因此是 i - 2
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        /**
         * 如果 pattern[j] == str[i] || pattern[j] == '.', 此时dp[i][j] = dp[i-1][j-1];
         * 如果 pattern[j] == '*'
         *     分两种情况:
         *     1: 如果pattern[j-1] != str[i] && pattern[j-1] != '.', 此时dp[i][j] = dp[i][j-2] //a*匹配0次
         *     2: 如果pattern[j-1] == str[i] || pattern[j-1] == '.'
         *         此时dp[i][j] = dp[i][j-2] // a*匹配0次
         *         或者 dp[i][j] = dp[i][j-1] // a*匹配1次
         *         或者 dp[i][j] = dp[i-1][j] // a*匹配多次
         */
        //s，p 都不为空
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //源字符串 s 和模式串 p 当前字符匹配：s[i - 1] == p[j - 1] || p[j - 1] == '.'
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //模式串当前字符为*，又分两种情况
                else if (p.charAt(j - 1) == '*') {
                    //情况1：源字符串当前字符 与 模式串*前的字符不匹配，a*只能匹配0次
                    // p[j - 1] == '*' && ( s[i - 1] != p[j - 2] && p[j - 2] != '.' )
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    }
                    /**
                     * 情况2：源字符串当前字符 与 模式串*前的字符匹配
                     * a*匹配0次：dp[i][j - 2]
                     * a*匹配1次：dp[i][j - 1]
                     * a*匹配多次：dp[i - 1][j]
                     *
                     * 注意：
                     * s = "ab"
                     * p = ".*"
                     * Output: true
                     */
                    else {
                        dp[i][j] = (dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j]);
                        //dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
