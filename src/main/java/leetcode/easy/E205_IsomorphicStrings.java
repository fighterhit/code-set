package leetcode.easy;

import java.util.Arrays;
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
            //若map包含key
            if (map.containsKey(cKey)) {
                //map包含key但val不相等，返回false
                if (!map.get(cKey).equals(cVal)) {
                    return false;
                }
            } else {
                //map不包含key但包含值，返回false
                if (map.containsValue(cVal)) {
                    return false;
                }
                map.put(cKey, cVal);
            }
        }
        return true;
    }

    /**
     * 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
     * 注意默认值都是0，因此赋值时为了避免是默认值和真实索引相等，需要i + 1；或者统一初始化为 -1
     */
    public boolean isIsomorphic2(String s, String t) {
        int[] preIndexOfS = new int[256];
        int[] preIndexOfT = new int[256];
        Arrays.fill(preIndexOfS, -1);
        Arrays.fill(preIndexOfT, -1);
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);
            if (preIndexOfS[sc] != preIndexOfT[tc]) {
                return false;
            }
            preIndexOfS[sc] = i;
            preIndexOfT[tc] = i;
        }
        return true;
    }
}
