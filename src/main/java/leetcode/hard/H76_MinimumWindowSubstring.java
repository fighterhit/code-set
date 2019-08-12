package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * <p>
 * https://www.cnblogs.com/grandyang/p/4340948.html
 * 滑动窗口 Sliding Window 问题，简直是神器啊，能解很多子串，子数组，子序列等等的问题
 */
public class H76_MinimumWindowSubstring {
    //代码面试指南
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] map = new int[128];
        for (char c : chart) {
            map[c]++;
        }
        String res = "";
        //l、r 分别表示窗口左右边界
        int l = 0, r = 0;
        //match表示"欠"的字符数
        int minLen = Integer.MAX_VALUE, match = chart.length;
        for (r = 0; r < chars.length; r++) {
            map[chars[r]]--;
            if (map[chars[r]] >= 0) {
                match--;
            }
            //找到包含 t 的子串
            if (match == 0) {
                while (map[chars[l]] < 0) {
                    map[chars[l]]++;
                    l++;
                }
                if (minLen > r - l + 1) {
                    minLen = r - l + 1;
                    res = s.substring(l, r + 1);
                }
                match++;
                map[chars[l]]++;
                l++;
            }
        }
        return res;
    }

    /**
     * https://www.jianshu.com/p/ce80b4c07c22
     * 首先预处理T，用一个128长 (示例代码用了256) 的整数数组srcHash存储里面每个目标字符出现的个数
     * 然后处理原串S，也用一个128长的整数数组destHash记录原串字符出现的个数。给定两个指针start和end，作为最小窗口的左右边界。
     */
    public String minWindow2(String s, String t) {
        int[] tArr = new int[128];
        for (char c : t.toCharArray()) {
            tArr[c]++;
        }
        char[] sArr = s.toCharArray();
        //统计每次遍历到字符的出现次数
        int[] des = new int[128];
        //res 是最后要求的最短子串，begin 是最后要求的最短子串 res 在 s 的起始位置，minLen 是最后要求的最短子串长度
        String res = null;
        int begin = -1, end = -1, minLen = Integer.MAX_VALUE;
        //start 是滑动窗口左侧起始位置，i 是遍历的位置，minLen
        int start, i, found = 0;
        for (i = start = 0; i < s.length(); i++) {
            char c = sArr[i];
            des[c]++;
            if (des[c] <= tArr[c]) {
                found++;
            }
            if (found == t.length()) {
                //左侧收缩
                while (start < i && des[sArr[start]] > tArr[sArr[start]]) {
                    des[sArr[start]]--;
                    start++;
                }
                if (i - start < minLen) {
                    minLen = i - start + 1;
                    begin = start;
                    end = i;
                }
                //更新起始位置
                des[sArr[start]]--;
                found--;
                start++;
            }
        }
        //substring 只有两个方法：
        //substring(int beginIndex)
        //substring(int beginIndex, int endIndex)
        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, end + 1);
    }
}
