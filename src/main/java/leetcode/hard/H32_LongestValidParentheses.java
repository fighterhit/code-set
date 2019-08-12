package leetcode.hard;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * <p>
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 * <p>
 * https://leetcode.com/problems/longest-valid-parentheses/solution/
 */
public class H32_LongestValidParentheses {

    //DP：只在 ")" 结尾时更新
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                //情况1："()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                }
                //情况2："))"
                else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //Stack ")()())"  "(()"

    /**
     * 定义start变量来记录合法括号串的起始位置
     * 遍历字符串，如果遇到左括号，则将当前下标压入栈；如果遇到右括号，如果当前栈为空，则将下一个坐标位置记录到start，如果栈不为空，则将栈顶元素取出，此时若栈为空，则更新结果和i - start + 1中的较大值，否则更新结果和i - 栈顶元素中的较大值
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //真正的开始位置前，应对")()())"开始就为 ")"
        int start = 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    //最终记录合法括号串左括号起始位置
                    start = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start + 1);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

    public int longestValidParentheses3(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //真正的开始位置前，应对")()())"开始就为 ")"
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                //和法2类似，相当于合并，将起始位置前一位置压入栈中，统一了 res = Math.max(res, i - stack.peek()) 计算
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    public int longestValidParentheses4(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //真正的开始位置前，应对")()())"开始就为 ")"
        int res = 0, l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                res = Math.max(res, 2 * r);
            }
            if (r > l) {
                l = r = 0;
            }
        }
        l = r = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }

            if (l == r) {
                res = Math.max(res, 2 * l);
            }
            if (l > r) {
                l = r = 0;
            }
        }
        return res;
    }
}
