package sword.ch3;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * @author Fighter Created on 2018/5/6.
 */
public class P113_Power {

    //更严谨的，判断底数是否为0

    public static boolean invalidInput = false;

    //注意判断浮点数相等应用近似判断
    public static boolean equal(double x, double y) {
        return -0.00001 < x - y && x - y < 0.00001;
    }

    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            if (equal(0, base)) {
                invalidInput = true;
                return 0;
            } else {
                return 1 / powerWithPositiveExponent(base, -exponent);
            }
        } else {
            return powerWithPositiveExponent(base, exponent);
        }
    }

    private static double powerWithPositiveExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if ((exponent & 0x1) == 1) {
            double res = powerWithPositiveExponent(base, exponent >> 1);
            return res * res * base;
        } else {
            double res = powerWithPositiveExponent(base, exponent >> 1);
            return res * res;
        }
    }


    //能通过的
    public static double Power(double base, int exponent) {
        double res = Power(base, Math.abs(exponent), 1);
        return exponent < 0 ? 1 / res : res;
    }

    public static double Power(double base, int exponent, int a) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double result = Power(base, exponent >> 1);
        result *= result;
        // 若是偶数
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("2^3=" + power(2, 3) + "\t是否报错:" + invalidInput);
        System.out.println("2^-3=" + power(2, -3) + "\t是否报错:" + invalidInput);
        System.out.println("0^3=" + power(0, 3) + "\t是否报错:" + invalidInput);
        System.out.println("0^-3=" + power(0, -3) + "\t是否报错:" + invalidInput);
    }
}
