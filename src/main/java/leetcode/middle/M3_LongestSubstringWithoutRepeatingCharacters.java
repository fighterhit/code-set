package leetcode.middle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * <p>
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class M3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] frequency = new int[256];
        int res = 0;
        int l = 0;
        for (int r = 0; r < s.length(); ) {
            //右边界能扩展
            if (frequency[s.charAt(r)] == 0) {
                res = Math.max(res, r - l + 1);
                frequency[s.charAt(r)]++;
                r++;
            }
            //有边界不能扩展，只能左边向右移动
            else {
                frequency[s.charAt(l)]--;
                l++;
            }
        }
        return res;
    }

    //动态规划：思路与上述方法一致
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] preIndex = new int[256];
        Arrays.fill(preIndex, -1);
        //因为循环从1开始，这里初始化第0位字符位置
        preIndex[s.charAt(0)] = 0;

        //以第i位字符为结尾的不包含重复字符的子字符串的最长长度
        int[] dp = new int[s.length()];
        dp[0] = 1;

        //注意非空串的最长不重复子串至少为1
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            int distance = i - preIndex[c];
            //第i位字符未出现过 或 与上次出现位置的距离 distance > dp[i - 1]
            if (preIndex[c] == -1 || distance > dp[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = distance;
            }
            max = Math.max(max, dp[i]);
            preIndex[c] = i;
        }
        return max;
    }

    //把出现过的字符都放入set中，遇到set中没有的字符就加入set中并更新结果res，如果遇到重复的，则从左边开始删字符，直到删到重复的字符停止
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int l = 0, r = 0;
        int max = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (!set.contains(c)) {
                set.add(c);
                r++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(l));
                l++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        new M3_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring2("abcabcbb");
    }
}
