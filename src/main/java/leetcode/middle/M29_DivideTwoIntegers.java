package leetcode.middle;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * <p>
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 * <p>
 * 定义变量t等于除数，定义计数p，当t的两倍小于等于被除数时，进行如下循环，t扩大一倍，p扩大一倍，然后更新res和m
 */
public class M29_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long d1 = Math.abs((long) dividend), d2 = Math.abs((long) divisor);
        if (d1 < d2) {
            return 0;
        }
        int res = 0;
        while (d1 >= d2) {
            long t = d2;
            int p = 1;
            while (d1 > (t << 1)) {
                t <<= 1;
                p <<= 1;
            }
            res += p;
            d1 -= t;
        }
        //同号为1 异号为-1
//        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
//        return res * sign;
        //同号为 false，异号为 true
        if ((dividend > 0) ^ (divisor > 0)) {
            res = -res;
        }
        return res;
    }

    public static void main(String[] args) {
        new M29_DivideTwoIntegers().divide(-2147483648, -1);
    }
}
