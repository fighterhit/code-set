package leetcode.easy;

import java.util.Arrays;

/**
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class E67_AddBinary {

    /**
     * 用了两个指针分别指向a和b的末尾，然后每次取出一个字符，转为数字，若无法取出字符则按0处理。
     * 然后定义进位carry，初始化为0，将三者加起来，对2取余即为当前位的数字，对2取商即为当前进位的值，记得最后还要判断下carry，如果为1的话，要在结果最前面加上一个1
     */
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, sum = 0, carry = 0;
        while (i >= 0 || j >= 0) {
            int a1 = i < 0 ? 0 : a.charAt(i) - '0', b1 = j < 0 ? 0 : b.charAt(j) - '0';
            sum = a1 + b1 + carry;
            carry = sum / 2;
            res.insert(0, sum % 2);
            i--;
            j--;
        }
        if (carry != 0) {
            res.insert(0, 1);
        }
        return res.toString();
    }

    public String addBinary2(String a, String b) {
        StringBuilder res = new StringBuilder();
        StringBuilder asb = new StringBuilder(a), bsb = new StringBuilder(b);
        int aLen = a.length(), bLen = b.length(), maxLen = aLen;
        //将长度短的字符串先补齐到和长的字符串长度相等
        if (aLen > bLen) {
            maxLen = aLen;
            for (int i = 0; i < aLen - bLen; i++) {
                bsb.insert(0, '0');
            }
        } else if (aLen < bLen) {
            maxLen = bLen;
            for (int i = 0; i < bLen - aLen; i++) {
                asb.insert(0, '0');
            }
        }
        //按位加
        int sum = 0, carray = 0;
        for (int i = maxLen - 1; i >= 0; i--) {
            sum = asb.charAt(i) - '0' + bsb.charAt(i) - '0' + carray;
            carray = sum / 2;
            res.insert(0, sum % 2);
        }
        if (carray != 0) {
            res.insert(0, '1');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        new E67_AddBinary().addBinary("11", "1");
    }
}
