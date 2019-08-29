package leetcode.easy;

/**
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * 注意:
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 示例 1:
 * 输入: 5
 * 输出: 2
 * 解释: 5的二进制表示为101（没有前导零位），其补数为010。所以你需要输出2。
 * 示例 2:
 * 输入: 1
 * 输出: 0
 * 解释: 1的二进制表示为1（没有前导零位），其补数为0。所以你需要输出0。
 */
public class E476_NumberComplement {
    /**
     * x ^ 1s = ~x
     * 1s 表示一串 1
     * 问题转化为先求掩码
     */
    public static int findComplement(int num) {
        if (num == 0) return 1;
        int mask = 1 << 30;
        //while 循环等价于 mask = Integer.highestOneBit(num); 得到和 num 最高位为 1 其余全为 0 的数
        while ((mask & num) == 0) {
            mask >>= 1;
        }
        //注意，应再往左移 1 位再减 1
        mask = (mask << 1) - 1;
        return num ^ mask;
    }
}
