package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * @author Fighter Created on 2018/7/1.
 */
public class M131_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        helper(res, tmp, s);
        return res;
    }

    private void helper(List<List<String>> res, List<String> tmp, String s) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            //注意这里判断是否前 i 个字符构成回文串
            if (isPalidrom(s.substring(0, i + 1))) {
                tmp.add(s.substring(0, i + 1));
                helper(res, tmp, s.substring(i + 1));
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    boolean isPalidrom(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
