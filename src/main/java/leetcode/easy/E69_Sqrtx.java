package leetcode.easy;

/**
 * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。可以利用二分查找在 0 ~ x 之间查找 sqrt。
 * <p>
 * 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。
 * 在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，因此最后的返回值应该为 h 而不是 l。
 * https://www.cnblogs.com/grandyang/p/4346413.html
 */
public class E69_Sqrtx {
    public int mySqrt(int x) {
        int l = 1, r = x;
        while (l <= r) {
            //求平方根，理论最后两个乘数应该一样
            //这里通过固定一个乘数 mid1（中间值），判断 另一个乘数 mid2 与 固定乘数 大小来调整固定乘数的变化方向
            int mid1 = l + (r - l >> 1);
            int mid2 = x / mid1;
            if (mid2 == mid1) {
                return mid1;
            } else if (mid2 > mid1) {
                l = mid1 + 1;
            } else {
                r = mid1 - 1;
            }
        }
        return r;
    }

    //牛顿开方法：参考 SquareRoot
    public int mySqrt2(int number) {
        if (number < 0) {
            return -1;
        }
        double err = 1e-3;
        //初始化平方根
        double root = number;
        while (Math.abs(number - root * root) > err) {
            root = (root + number / root) / 2;
        }
        return (int) root;
    }

    public static void main(String[] args) {
        int t = new E69_Sqrtx().mySqrt(2147395599);
        System.out.println(t);
    }
}
