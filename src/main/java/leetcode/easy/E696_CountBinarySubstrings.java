package leetcode.easy;

/**
 * 从中间向两边扩展
 * 对比 M647_PalindromicSubstrings M5_LongestPalindromicSubstring
 */
public class E696_CountBinarySubstrings {

    int cnt = 0;

    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            //注意从相邻两个不等的字符开始向两边扩展
            if (chars[i] != chars[i + 1]) {
                cnt++;
                helper(chars, i, i + 1);
            }
        }
        return cnt;
    }

    private void helper(char[] chars, int l, int r) {
        for (; l - 1 >= 0 && r + 1 < chars.length; ) {
            if (chars[l] == chars[l - 1] && chars[r] == chars[r + 1]) {
                cnt++;
                l--;
                r++;
            } else {
                break;
            }
        }
    }
}
