package leetcode.middle;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class M139_WordBreak {
    //DP
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int maxLen = 0;
        for (String s1 : wordDict) {
            maxLen = Math.max(maxLen, s1.length());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                //剪枝：超过词典最大单词长度直接停止
                if (i - j > maxLen) {
                    break;
                }
                if (dp[j] && wordDict.indexOf(s.substring(j, i)) >= 0) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    //未优化DP
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int maxLen = 0;
        for (String s1 : wordDict) {
            maxLen = Math.max(maxLen, s1.length());
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.indexOf(s.substring(j, i)) >= 0) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    int[] memo;
    HashSet<String> set = new HashSet<>();

    //超时
    public boolean wordBreak3(String s, List<String> wordDict) {
        for (String s1 : wordDict) {
            set.add(s1);
        }
        memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        helper(s, 0);
        return memo[0] == 1;
    }

    private boolean helper(String s, int start) {
        if (s.length() == start) {
            return true;
        }
        if (memo[start] != -1) {
            return memo[start] == 1;
        }
        for (int i = start; i < s.length(); i++) {
            String ss = s.substring(start, i + 1);
            if (set.contains(ss) && helper(s, i + 1)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> ls = new ArrayList() {{
            add("leet");
            add("code");
        }};
        new M139_WordBreak().wordBreak3("leetcode", ls);
    }
}
