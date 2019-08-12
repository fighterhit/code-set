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
 * <p>
 * 参考 BigNumberMultiply
 * FFT: https://leetcode-cn.com/problems/multiply-strings/solution/fftde-nlognjie-fa-by-qqktr/
 */
public class M43_MultiplyStrings {
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        int[] res = new int[n1 + n2];

        for (int i = 0; i < n1; i++) {
            arr1[i] = num1.charAt(i) - '0';
        }

        for (int i = 0; i < n2; i++) {
            arr2[i] = num2.charAt(i) - '0';
        }

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                res[i + j + 1] += arr1[i] * arr2[j];
            }
        }

        for (int i = n1 + n2 - 1; i > 0; i--) {
            if (res[i] >= 10) {
                res[i - 1] += res[i] / 10;
                res[i] = res[i] % 10;
            }
        }

        //删除前导0，注意判断条件
        StringBuilder sb = new StringBuilder();
        for (int n : res) {
            if (sb.length() != 0 || n != 0) {
                sb.append(n);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String multiply2(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length(), n2 = num2.length();
        int[] res = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                res[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                res[i + j] += res[i + j + 1] / 10;
                res[i + j + 1] %= 10;
            }
        }

        for (int p : res) {
            //删除前导0，注意判断条件
//            if (!(sb.length() == 0 && p == 0)) {
            if (sb.length() != 0 || p != 0) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        new M43_MultiplyStrings().multiply2("5", "0");
    }
}
