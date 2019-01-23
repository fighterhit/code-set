package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * @author Fighter.
 * 字符串合法性
 * 空字符串
 * 多个解的顺序
 * 和 M46_Permutations 区别
 */
public class M17_LetterCombinationsofaPhoneNumber {

    String[] letterMaps = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        findCombinations(digits, 0, "");
        return res;
    }

    private void findCombinations(String digits, int index, String s) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }
        int mapIndex = digits.charAt(index) - '0';
        String letter = letterMaps[mapIndex];
        for (int i = 0; i < letter.length(); i++) {
            findCombinations(digits, index + 1, s + letter.charAt(i));
        }
    }
}
