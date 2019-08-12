package leetcode.middle;

import java.util.Arrays;

/**
 * Given an input string, reverse the string word by word.
 * <p>
 * Example 1:
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * <p>
 * Example 2:
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * <p>
 * Example 3:
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * Note:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class M151_ReverseWordsinaString {
    public String reverseWords(String s) {
        if (s == null || s.trim().length() == 0) {
            return "";
        }
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].isEmpty()) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
}
