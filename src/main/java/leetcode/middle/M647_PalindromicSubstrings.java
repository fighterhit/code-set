package leetcode.middle;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * Note:
 * The input string length won't exceed 1000.
 * <p>
 * 计算有多少个回文子字符串
 * 对比 E696_CountBinarySubstrings M5_LongestPalindromicSubstring
 * <p>
 * 以字符串中的每一个字符都当作回文串中间的位置，然后向两边扩散，每当成功匹配两个左右两个字符，结果cnt自增1，然后再比较下一对。
 * 注意回文字符串有奇数和偶数两种形式：
 * 如果是奇数长度，那么i位置就是中间那个字符的位置，所以左右两遍都从i开始遍历
 * 如果是偶数长度，那么i是最中间两个字符的左边那个，右边那个就是i+1，这样就能cover所有的情况，而且都是不同的回文子字符串
 * https://www.cnblogs.com/grandyang/p/7404777.html
 */
public class M647_PalindromicSubstrings {
    int cnt = 0;

    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            //奇数
            palindrom(chars, i, i);
            //偶数
            palindrom(chars, i, i + 1);
        }
        return cnt;
    }

    void palindrom(char[] chars, int l, int r) {
        for (; l >= 0 && r < chars.length; ) {
            if (chars[l] != chars[r]) {
                break;
            }
            l--;
            r++;
            cnt++;
        }
    }

    /**
     * dp[i][j] 为区间 [i,j] 是否为回文的标志, 对应此状态的子问题可从回文的定义出发，如果字符串首尾字符相同且在去掉字符串首尾字符后字符串仍为回文，则原字符串为回文。
     * 状态转移方程 dp[i][j] = s[i] == s[j] && dp[i+1][j-1], 由于状态转移方程中依赖比i大的结果，故实现中需要从索引大的往索引小的递推，另外还需要考虑一些边界条件和初始化方式
     * https://algorithm.yuanbin.me/zh-hans/dynamic_programming/palindrome_partitioning_ii.html#%E9%A2%98%E8%A7%A32---%E4%BD%BF%E7%94%A8%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E8%AE%A1%E7%AE%97%E5%AD%90%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%9B%9E%E6%96%87%E7%8A%B6%E6%80%81
     */
    public int countSubstrings2(String s) {
        int cnt = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    //思路同上，简洁
    public int countSubstrings3(String s) {
        int cnt = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                    cnt++;
                } else if (j == i + 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        cnt++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
