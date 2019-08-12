package leetcode.hard;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class H140_WordBreakII {

    Map<String, List<String>> map = new HashMap<>();

    //超时
    public List<String> wordBreak(String s, List<String> wordDict) {
        return helper(s, wordDict);
    }

    private List<String> helper(String s, List<String> dict) {
        if (s.length() == 0) {
            return new ArrayList() {{
                add("");
            }};
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for (String w : dict) {
            //如果词典中当前单词与字符串前缀匹配
            if (w.length() > s.length() || !s.substring(0, w.length()).equals(w)) {
                continue;
            }
            List<String> ls = helper(s.substring(w.length()), dict);
            for (int i = 0; i < ls.size(); i++) {
                String tmpRes = ls.get(i);
                if (tmpRes.length() == 0) {
                    res.add(w);
                } else {
                    res.add(w + " " + tmpRes);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {

        List<String> r = helper2(s, wordDict);
        for (int i = 0; i < r.size(); i++) {
            String t = r.get(i);
            //移除字符串最后两个空格
            r.set(i, t.substring(0, t.length() - 2));
        }
        return r;
    }

    private List<String> helper2(String s, List<String> dict) {
        if (s.length() == 0) {
            return new ArrayList() {{
                add(" ");
            }};
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for (String w : dict) {
            //如果词典中当前单词与字符串前缀匹配
            if (w.length() > s.length() || !s.substring(0, w.length()).equals(w)) {
                continue;
            }
            List<String> ls = helper(s.substring(w.length()), dict);
            for (int i = 0; i < ls.size(); i++) {

                String tmpRes = ls.get(i);
                //每个单词后都加空格，这样会导致最后字符串末尾是两个空格，需要统一移除
                String r = w + " " + tmpRes;
                //注意：这样回溯时每一步都删除最后一个字符
                res.add(r);
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        new H140_WordBreakII().wordBreak("catsanddog", Arrays.asList(new String[]{
                "cat", "cats", "and", "sand", "dog"
        }));
    }
}
