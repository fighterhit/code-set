package NewCoderProblemSet.Other;

/**
 * 牛顿迭代法求平方根，严格根据公式来，设置误差，当 Xn+1 的平方与 number 差的绝对值小于误差时，Xn+1 即为平方根
 * https://www.guokr.com/question/461510/?answer=481572#answer481572
 * https://blog.csdn.net/chenrenxiang/article/details/78286599
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
