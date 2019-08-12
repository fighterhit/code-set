package leetcode.hard;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * Example 1:
 * Input:
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * <p>
 * Example 2:
 * Input:
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * Output: []
 * https://www.cnblogs.com/grandyang/p/4521224.html
 */
public class H30_SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || s.isEmpty() || words.length == 0) {
            return res;
        }
        int m = s.length(), n = words.length, wLen = words[0].length();
        //统计 words 每个字符串次数，可能重复
        Map<String, Integer> cntMap = new HashMap<>();
        for (String word : words) {
            cntMap.put(word, cntMap.getOrDefault(word, 0) + 1);
        }
        //
        for (int i = 0; i <= m - n * wLen; i++) {
            //一共要匹配 words 里的 n 个子字符串
            Map<String, Integer> tmpMap = new HashMap<>();
            int j = 0;
            for (j = 0; j < n; j++) {
                int startIndex = i + j * wLen, endIndex = i + (j + 1) * wLen;
                String tmp = s.substring(startIndex, endIndex);
                //若 cntMap 没有当前字符串，则退出当前循环从 i 下一个位置开始
                if (!cntMap.containsKey(tmp)) {
                    break;
                }
                tmpMap.put(tmp, tmpMap.getOrDefault(tmp, 0) + 1);
                //若当前字符串出现次数大于 words 中当前字符串次数，则退出当前循环从 i 下一个位置开始
                if (tmpMap.get(tmp) > cntMap.get(tmp)) {
                    break;
                }
            }
            //遍历完所有子串，说明s中存在匹配的子串
            if (j == n) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new H30_SubstringwithConcatenationofAllWords().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
    }
}
