package leetcode.middle;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 * Note: "aba" is also a valid answer.
 * 对比 E696_CountBinarySubstrings M647_PalindromicSubstrings
 */
public class M5_LongestPalindromicSubstring {
    //O(n^2)
    //传统的验证回文串的方法就是两个两个的对称验证是否相等，那么对于找回文字串的问题，就要以每一个字符为中心，像两边扩散来寻找回文串，这个算法的时间复杂度是 O(n*n)
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        int maxLen = 0, left = 0;
        //从回文串中间位置开始枚举，向两边扩展
        for (int i = 0; i < chars.length; i++) {
            //奇数情况
            int l = palindrom(chars, i, i);
            int tmpLen = 2 * (i - l) + 1;
            if (maxLen < tmpLen) {
                maxLen = tmpLen;
                left = l;
            }

            //偶数情况
            l = palindrom(chars, i, i + 1);
            tmpLen = 2 * (i - l + 1);
            if (maxLen < tmpLen) {
                maxLen = tmpLen;
                left = l;
            }
        }
        return s.substring(left, left + maxLen);
    }

    int palindrom(char[] chars, int l, int r) {
        //返回回文串最左字符的索引
        for (; l >= 0 && r < chars.length; l--, r++) {
            if (chars[l] != chars[r]) {
                break;
            }
        }
        return l + 1;
    }

    //O(n^2)
    public String longestPalindrome2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        String res = null;
        int n = chars.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                //两端字符相同
                if (chars[i] == chars[j]) {
                    // i - j <= 1 ：i、j 指向相同字符 或者 指向相邻字符
                    //两端字符相同情况下 内部是回文串
                    if (i - j <= 1 || dp[j + 1][i - 1]) {
                        //标记
                        dp[j][i] = true;
                        if (res == null || res.length() < i - j + 1) {
                            res = s.substring(j, i + 1);
                        }
                    }
                }
            }
        }
        return res;
    }
}
