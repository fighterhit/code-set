package leetcode.middle;

import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * @author Fighter Created on 2018/6/29.
 */
public class M43_MultiplyStrings {
    public String multiply(String num1, String num2) {

        return null;
    }

    // 第1个数大，第2个数小
    public static int[] oneBigOneSmall(int[] num1, int num2) {
        for (int i = 0; i < num1.length; i++) {
            num1[i] *= num2;
        }
        for (int i = num1.length - 1; i > 0; i--) {
            num1[i - 1] += num1[i] / 10;
            num1[i] = num1[i] % 10;
        }
        System.out.println(Arrays.toString(num1));
        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[arr.length - 1] = 2;
        arr[arr.length - 2] = 3;
        arr[arr.length - 3] = 7;
        System.out.println(Arrays.toString(arr));
        oneBigOneSmall(arr, 16);
    }
}
