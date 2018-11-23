package leetcode.easy;

import java.util.*;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * <p>
 * Example 2:
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * <p>
 * Example 3:
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * <p>
 * Example 4:
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class E290_WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] strArr = str.split(" ");
        if (strArr.length != pattern.length()) {
            return false;
        }
        Map<Character, String> patternMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (patternMap.containsKey(c)) {
                if (!patternMap.get(c).equals(strArr[i])) {
                    return false;
                }
            } else if (!patternMap.containsKey(c)) {
                if (patternMap.containsValue(strArr[i])) {
                    return false;
                }
                patternMap.put(c, strArr[i]);
            }
        }
        return true;
    }
}