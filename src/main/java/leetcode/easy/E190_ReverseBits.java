package leetcode.easy;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * Example 1:
 * Input: 00000010100101000001111010011100
 * Output: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * <p>
 * Example 2:
 * Input: 11111111111111111111111111111101
 * Output: 10111111111111111111111111111111
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.
 * Note:
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * <p>
 * 翻转对应整数的二进制表示，返回新表示的整数
 */
public class E190_ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            //末尾腾出一位 0，后面进行判断是否应当设置为 1
            res <<= 1;
            //若 n 末位为1，则将res++，即 res 末位应设为 1
            if ((n & 1) == 1) {
                res++;
            }
            //将 n 右移 1 位，表示清除最低位
            n >>>= 1;
        }
        return res;
    }

    public int reverseBits2(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                sb.insert(0, 1);
            } else {
                sb.insert(0, 0);
            }
            n >>>= 1;
        }
        return Integer.parseUnsignedInt(sb.reverse().toString(), 2);
    }
}
