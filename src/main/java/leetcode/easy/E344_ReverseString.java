package leetcode.easy;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * <p>
 * Example 1:
 * Input: "hello"
 * Output: "olleh"
 * <p>
 * Example 2:
 * Input: "A man, a plan, a canal: Panama"
 * Output: "amanaP :lanac a ,nalp a ,nam A"
 */
public class E344_ReverseString {
    public String reverseString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0, j = len - 1; i < chars.length / 2; ) {
            swap(chars, i++, j--);
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        new E344_ReverseString().reverseString(new String("hello"));
    }
}
