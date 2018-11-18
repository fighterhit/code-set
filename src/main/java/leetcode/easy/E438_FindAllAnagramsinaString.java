package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class E438_FindAllAnagramsinaString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.isEmpty() || p.isEmpty() || p.length() > s.length()) {
            return res;
        }
        int sLen = s.length();
        int pLen = p.length();
        int[] sMap = new int[256];
        int[] pMap = new int[256];
        for (int i = 0; i < p.length(); i++) {
            pMap[p.charAt(i)]++;
            sMap[s.charAt(i)]++;
        }
        if (same(sMap, pMap)) {
            res.add(0);
        }
        for (int i = pLen; i < sLen; i++) {
            sMap[s.charAt(i)]++;
            sMap[s.charAt(i - pLen)]--;
            if (same(sMap, pMap)) {
                res.add(i - pLen + 1);
            }
        }
        return res;
    }

    /*public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.isEmpty() || p.isEmpty()) {
            return res;
        }
        int sLen = s.length();
        int pLen = p.length();
        int[] sMap = new int[256];
        int[] pMap = new int[256];
        for (int i = 0; i < p.length(); i++) {
            pMap[p.charAt(i)]++;
        }
        for (int i = 0; i < sLen; i++) {
            //i 大于等于p串长度，则把最左边字符去掉（频率减一）
            if (i >= pLen) {
                sMap[s.charAt(i - pLen)]--;
            }
            sMap[s.charAt(i)]++;
            if (same(sMap, pMap)) {
                res.add(i - pLen + 1);
            }
        }
        return res;
    }*/

    boolean same(int[] sMap, int[] pMap) {
        for (int i = 0; i < sMap.length; i++) {
            if (sMap[i] != pMap[i]) {
                return false;
            }
        }
        return true;
    }
}
