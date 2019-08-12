package leetcode.easy;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 * Input: 123
 * Output: 321
 * <p>
 * Example 2:
 * Input: -123
 * Output: -321
 * <p>
 * Example 3:
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * <p>
 * 注意反转会发生越界问题：上界2147483647, 下界-2147483648. 反过来看7463847412 ->  2147483647,  -8463847412 -> -2147483648
 */
public class E7_ReverseInteger {
    public int reverse(int x) {
        int res = 0;
        //注意这里 x 可以为负数，因此这里是x != 0 而不是 x > 0
        while (x != 0) {
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(-123 % -4);
    }
}
