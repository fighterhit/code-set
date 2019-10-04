package algorithm.Bit;

/**
 * 不用算术运算实现整数加减乘除运算
 * 给定 32 位整数 a 和 b，可正、可负、可 0。不能使用算术运算符，分别实现 a 和 b 的加减乘除运算
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class OpByBit {
    /**
     * 加法
     * 1. 不考虑进位情况下，a^b 就是正确结果
     * 2. 只算进位情况下，即只考虑 a 加 b 的过程中产生的进位值是什么，即 (a&b) << 1，因为在第 i 位上只有 1 与 1 相加才会产生 i-1 位的进位
     * 3. 把完全不考虑进位的相加值与只考虑进位相加的值再相加，就是最终结果。
     */
    public int add(int a, int b) {
        int sum = 0;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return a;
    }

    public int add2(int a, int b) {
        return b == 0 ? a : add2(a ^ b, (a & b) << 1);
    }

    /**
     * 减法
     * 实现 a-b 等价于 a+(-b)，得到相反数就是这个数的二进制表达取反加 1 (补码)的结果
     */
    //获取 num 的相反数
    public int negNum(int num) {
        return add(~num, 1);
    }

    public int minus(int a, int b) {
//        return add(a, -b);
        return add(a, negNum(b));
    }

    /**
     * 乘法
     * 参考竖式乘法
     * https://blog.csdn.net/sinat_35261315/article/details/78376690
     */

    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            //每次 b 右移 1 位，a 左移 1 位
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 除法
     */
    //判断 n 是否为负数
    public boolean isNeg(int n) {
        return n < 0;
    }

    public int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            //被除数通过移位，找到第一个比恰好大于等于除数 y 的值
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    //为了处理整数 Integer.MIN_VALUE
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            int res = div(add(a, 1), b);
            res = add(div(minus(a, multi(res, b)), b), res);
            return res;
        } else {
            return div(a, b);
        }
    }
}
