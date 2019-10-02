package NewCoderProblemSet.Other.alibaba.P2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京”， 要求输入一个匹配模式（简单的以字符来写）， 比如 aabb, 来判断该字符串是否符合该模式， 举个例子：
 * 1. pattern = "abba", str="北京 杭州 杭州 北京" 返回 true
 * 2. pattern = "aabb", str="北京 杭州 杭州 北京" 返回 false
 * 3. pattern = "baab", str="北京 杭州 杭州 北京" 返回 true
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine(), str = sc.nextLine();
        System.out.println(isValid(pattern, str));
    }

    public static boolean isValid(String pattern, String str) {
        String[] strs = str.split(" ");
        char[] pchars = pattern.toCharArray();
        if (pattern.length() != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char c = pchars[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strs[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(strs[i])) {
                    return false;
                }
                map.put(pchars[i], strs[i]);
            }
        }
        return true;
    }
}
