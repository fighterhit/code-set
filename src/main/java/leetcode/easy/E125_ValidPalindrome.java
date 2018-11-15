package leetcode.easy;

/**
 * 判断回文字符串
 * <p>
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * <p>
 * Example 2:
 * Input: "race a car"
 * Output: false
 */
public class E125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isNumberLetter(s.charAt(i))) {
                i++;
            }
            while (i < j && !isNumberLetter(s.charAt(j))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    boolean isNumberLetter(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static void main(String[] args) {
        new E125_ValidPalindrome().isPalindrome(",.");
    }
}
