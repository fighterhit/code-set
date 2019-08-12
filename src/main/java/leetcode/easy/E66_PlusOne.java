package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * Example 2:
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class E66_PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        List<Integer> res = new LinkedList<>();
        int len = digits.length, carry = 0, sum = 0, i = len - 1;
        do {
            sum = (i == len - 1) ? digits[i] + 1 : digits[i] + carry;
            carry = sum / 10;
            digits[i] = sum % 10;
            i--;
        } while (i >= 0 && carry != 0);
        for (int digit : digits) {
            res.add(digit);
        }
        if (carry != 0) {
            ((LinkedList<Integer>) res).addFirst(1);

        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //early return
    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            //若某一位小于 9，则加 1 后直接返回
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            //某一位为9，则将当前位置 0，加 1 操作交给下一个高位
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        new E66_PlusOne().plusOne(new int[]{1, 2, 3});
    }
}
