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
}
