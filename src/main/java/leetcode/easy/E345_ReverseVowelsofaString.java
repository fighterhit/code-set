package leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;

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

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels2(String s) {
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i <= j) {
            char ci = chars[i];
            char cj = chars[j];
            if (!vowels.contains(ci)) {
                //i 位置不是元音，放下继续
                chars[i++] = ci;
            } else if (!vowels.contains(cj)) {
                //j 位置不是元音，放下继续
                chars[j--] = cj;
            } else {
                //i、j 位置都是元音
                chars[i++] = cj;
                chars[j--] = ci;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        new E345_ReverseVowelsofaString().reverseVowels("aA");
    }
}
