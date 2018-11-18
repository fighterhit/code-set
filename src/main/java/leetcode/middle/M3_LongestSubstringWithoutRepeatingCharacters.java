package leetcode.middle;

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

    public static void main(String[] args) {
        new M3_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb");
    }
}
