package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class M22_GenerateParentheses {
    List<String> res;
    StringBuilder sb;
    int n;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        this.n = n;
        helper(0, 0, "");
        return res;
    }

    private void helper(int lCnt, int rCnt, String s) {
        if (rCnt == n) {
            res.add(s);
            return;
        }
        if (lCnt < n) {
            helper(lCnt + 1, rCnt, s + "(");
        }
        if (lCnt > rCnt) {
            helper(lCnt, rCnt + 1, s + ")");
        }
    }


    public List<String> generateParenthesis2(int n) {
        res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        this.n = n;
        sb = new StringBuilder();
        helper2(0, 0);
        return res;
    }

    private void helper2(int lCnt, int rCnt) {
        if (rCnt == n) {
            res.add(sb.toString());
            return;
        }
        if (lCnt < n) {
            sb.append("(");
            helper2(lCnt + 1, rCnt);
            //回溯后要删除上次最后添加的元素
            sb.deleteCharAt(sb.length() - 1);
        }
        if (lCnt > rCnt) {
            sb.append(")");
            helper2(lCnt, rCnt + 1);
            //回溯后要删除上次最后添加的元素
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
