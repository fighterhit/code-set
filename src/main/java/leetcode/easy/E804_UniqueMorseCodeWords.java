package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fighter Created on 2018/5/7.
 */
public class E804_UniqueMorseCodeWords {

    static String[] morseCode = new String[]{
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public static int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            // StringBuilder 非线程安全，单线程性能好
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(morseCode[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();

    }

    public static void main(String[] args) {
        uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});
    }
}
