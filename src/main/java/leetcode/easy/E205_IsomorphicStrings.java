package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * <p>
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 * 同290
 */
public class E205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character cKey = s.charAt(i);
            Character cVal = t.charAt(i);
            if (map.containsKey(cKey)) {
                if (!map.get(cKey).equals(cVal)) {
                    return false;
                }
            } else {
                if (map.containsValue(cVal)) {
                    return false;
                }
                map.put(cKey, cVal);
            }
        }
        return true;
    }
}
