package NewCoderProblemSet.Other;

/**
 * 牛顿迭代法求平方根，严格根据公式来，设置误差，当 Xn+1 的平方与 number 差的绝对值小于误差时，Xn+1 即为平方根
 * https://www.guokr.com/question/461510/?answer=481572#answer481572
 * https://blog.csdn.net/chenrenxiang/article/details/78286599
 * <p>
 * 参考 E69_Sqrtx 二分查找求平方根
 * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。可以利用二分查找在 0 ~ x 之间查找 sqrt。
 * 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。
 * 在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，因此最后的返回值应该为 h 而不是 l。
 */
public class SquareRoot {
    public static double squareRoot(double number) {
        if (number < 0) {
            return Double.NaN;
        }
        double err = 1e-15;
        //初始化平方根
        double root = number;
        while (Math.abs(number - root * root) > err) {
            root = (root + number / root) / 2;
        }
        return root;
    }

    public static void main(String[] args) {
        double num = squareRoot(4);
        System.out.println(num);
    }
}
