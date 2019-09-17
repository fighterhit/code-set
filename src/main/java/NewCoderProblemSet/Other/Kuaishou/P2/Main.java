package NewCoderProblemSet.Other.Kuaishou.P2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 参考 M17_LetterCombinationsofaPhoneNumber
 * dfs 回溯
 */
public class Main {
    static List<String> res = new ArrayList<>();
    static String[] letterMaps = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        helper(s, 0, "");
        System.out.println(res);
    }

    private static void helper(String digits, int index, String s) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }
        int mapIndex = digits.charAt(index) - '0';
        String letter = letterMaps[mapIndex];
        for (int i = 0; i < letter.length(); i++) {
            helper(digits, index + 1, s + letter.charAt(i));
        }
    }
}
