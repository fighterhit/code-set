package leetcode.easy;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 * <p>
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class E345_ReverseVowelsofaString {
    public String reverseVowels(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int i = 0, j = chars.length - 1;
        while (true) {
            while (i < j && !test(chars[i])) {
                i++;
            }
            while (i < j && !test(chars[j])) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(chars, i, j);
            i++;
            j--;
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    private boolean test(char aChar) {
        switch (aChar) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        new E345_ReverseVowelsofaString().reverseVowels("aA");
    }
}
