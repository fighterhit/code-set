package leetcode.easy;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class E242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] sMap = new int[256];
        int[] tMap = new int[256];
        for (int i = 0; i < s.length(); i++) {
            sMap[s.charAt(i)]++;
            tMap[t.charAt(i)]++;
        }
        return same(sMap, tMap);
    }

    boolean same(int[] sMap, int[] tMap) {
        for (int i = 0; i < tMap.length; i++) {
            if (tMap[i] != sMap[i]) {
                return false;
            }
        }
        return true;
    }
}
