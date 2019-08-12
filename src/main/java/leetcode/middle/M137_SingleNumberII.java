package leetcode.middle;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Example 1:
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 * 只有一个数字出现1次，其余出现三次.
 * 题解: 把所有出现三次的数字的二进制表示的每一位都分别加起来，那么每一位的和都能被 3 整除
 * <p>
 * P278_NumberAppearOnce, E136_SingleNumber, M260_SingleNumberIII
 */
public class M137_SingleNumberII {
    public int singleNumber(int[] nums) {
        int res = 0;
        //注意 i 最大取 8 * sizeof(int) - 1
        for (int i = 0; i < 32; i++) {
            //记录每一位的和
            int bitSum = 0;
            for (int n : nums) {
                //注意这里 无符号移位 >>> 或 有符号移位 >> 均可
                bitSum += (n >>> i) & 1;
            }
            //出现 3 次的数字每一位之和对 3 取余后都等于 0，那么最后 bitSum 对 3 取余就是出现 1 次的数字每一位上的数
            res |= (bitSum % 3) << i;
        }
        return res;
    }
}
