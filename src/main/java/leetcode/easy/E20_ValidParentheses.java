package leetcode.easy;

import java.util.HashMap;
import java.util.Stack;

public class E20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stringStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                stringStack.push(s.charAt(i));
            } else {
                if (stringStack.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == '}' && stringStack.pop() != '{') {
                    return false;
                }
                if (s.charAt(i) == ')' && stringStack.pop() != '(') {
                    return false;
                }
                if (s.charAt(i) == ']' && stringStack.pop() != '[') {
                    return false;
                }
            }
        }
        return stringStack.isEmpty();
    }

    //clean code
    public boolean isValid2(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.isEmpty() || stack.pop() != c) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    HashMap<Character, Character> map = new HashMap() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
    }};

    public boolean isValid3(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stk = new Stack();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stk.push(map.get(ch));
            } else if (stk.isEmpty() || stk.pop() != ch) {
                return false;
            }
        }
        return stk.isEmpty();
    }

    //拓展问题：只考虑小括号匹配问题 '( )'
    //计数法：参考 H32_LongestValidParentheses 方法4，这里只考虑从左往右扫描
    //括号匹配充要条件：1. 左右括号数相等 2. 任意位置左括号数 >= 右括号数
    public boolean isValid4(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int l = 0, r = 0;
        //循环内满足 l >= r; 若 r > l, 则直接返回 false;
        //循环外可能 l > r, 比如 ((()), 因此需再判断一次左右括号数是否相等
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }
            if (r > l) {
                return false;
            }
        }
        return l == r;
    }
}
