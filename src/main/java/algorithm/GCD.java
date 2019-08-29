package algorithm;

/**
 * 最大公约数（欧几里德算法证明）
 * https://blog.csdn.net/afei__/article/details/80216247
 */
public class GCD {
    //递归版本
    private static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        return a % b == 0 ? b : gcd(b, a % b);
    }

    //非递归版本
    private static int gcd2(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        while (a % b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }
}
