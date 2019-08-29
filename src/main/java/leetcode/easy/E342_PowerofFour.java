package leetcode.easy;

/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * 示例 1:
 * 输入: 16
 * 输出: true
 * 示例 2:
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class E342_PowerofFour {

    /**
     * 1,4,16,64...
     * 4 的幂肯定也是 2 的幂，因此只有 1 位为1，然后看幂次是不是 2 的正整数倍
     */
    public boolean isPowerOfFour(int num) {
        if (num > 0 && (num & num - 1) == 0) {
            int i = 0;
            for (; i < 32; i++) {
                if (((1 << i) & num) != 0) {
                    return i % 2 == 0;
                }
            }
        }
        return false;
    }

    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0b01010101010101010101010101010101) != 0;
    }
}
