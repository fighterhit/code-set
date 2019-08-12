package leetcode.middle;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * Example 1:
 * Input: 2.00000, 10
 * Output: 1024.00000
 * <p>
 * Example 2:
 * Input: 2.10000, 3
 * Output: 9.26100
 * <p>
 * Example 3:
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * <p>
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
 */
public class M50_Pow {
    //不能直接对负数指数取相反数，因为 n 可能为 -2147483648，取相反数溢出
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        double res = myPow(x, n / 2);
        //指数为偶数
        if (n % 2 == 0) {
            return res * res;
        }
        //指数为奇数
        else {
            if (n < 0) {
                return res * res / x;
            } else {
                return res * res * x;
            }
        }
    }

    //https://www.cnblogs.com/grandyang/p/4383775.html
    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        double res = 1;
        //注意 i /= 2
        for (int i = n; i != 0; i /= 2) {
            if ((i & 1) == 1) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}
