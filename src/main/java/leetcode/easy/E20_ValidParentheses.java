package leetcode.easy;

import java.util.Stack;

public class E20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stringStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='{'||s.charAt(i)=='('||s.charAt(i) == '['){
                stringStack.push(s.charAt(i));
            }else{
                if (stringStack.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == '}' && stringStack.pop() != '{'){
                    return false;
                }
                if (s.charAt(i)== ')' && stringStack.pop() != '('){
                    return false;
                }
                if (s.charAt(i) == ']' && stringStack.pop() != '['){
                    return false;
                }
            }
        }
        return stringStack.isEmpty();
    }
}
