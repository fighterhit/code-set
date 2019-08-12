package leetcode.easy;

/**
 * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。可以利用二分查找在 0 ~ x 之间查找 sqrt。
 * <p>
 * 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。
 * 在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，因此最后的返回值应该为 h 而不是 l。
 */
public class E69_Sqrtx {
    public int mySqrt(int x) {
        int l = 1, r = x, m = 0, sqrt = 0;
        while (l <= r) {
            m = l + (r - l >> 1);
            sqrt = x / m;
            if (sqrt == m) {
                return m;
            } else if (sqrt > m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        new E69_Sqrtx().mySqrt(8);
    }
}
