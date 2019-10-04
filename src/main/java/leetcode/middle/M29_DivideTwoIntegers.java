package leetcode.middle;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * <p>
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * 定义变量t等于除数，定义计数p，当t的两倍小于等于被除数时，进行如下循环，t扩大一倍，p扩大一倍，然后更新res和m
 * 参考 OpByBit
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
