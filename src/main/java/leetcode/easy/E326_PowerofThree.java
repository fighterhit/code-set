package leetcode.easy;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 示例 1:
 * 输入: 27
 * 输出: true
 * 示例 2:
 * 输入: 0
 * 输出: false
 * 示例 3:
 * 输入: 9
 * 输出: true
 * 示例 4:
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
 */
public class E326_PowerofThree {
    //一直除3取余数，如果最后余数是1，则是3的幂数
    public boolean isPowerOfThree(int n) {
        while (n > 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }
        return n == 1;
    }

    /**
     * 求3的指数是否为整数
     * 若 n 是 3 的幂则 i 是整数。在 Java 中，我们通过取小数部分（利用 % 1）来检查数字是否是整数，并检查它是否是 0。
     */
    public boolean isPowerOfThree2(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    /**
     * 3的幂数的特点:
     * 如果这个数是3的幂数，它所有的约数都是三的幂数.
     * 可以借助Math类的方法求出int不溢出情况下 最大的3的幂数，然后判断所输入的数x是否能被最大幂数整除。
     */
    public boolean isPowerOfThree3(int n) {
        //最大指数
        int zhishu = (int) (Math.log(Integer.MAX_VALUE) / Math.log(3));
        //整数范围内3的幂次方最大数
        int max = (int) Math.pow(3, zhishu);
        return n > 0 && max % n == 0;
    }
}
