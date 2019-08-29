package leetcode.middle;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * Example:
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * <p>
 * 只有两个数字出现一次，其余都出现两次
 * E136_SingleNumber, M137_SingleNumberII
 */
public class M260_SingleNumberIII {
    public int[] singleNumber(int[] nums) {

        int[] ret = new int[2];
        if (nums == null || nums.length == 0) {
            return ret;
        }

        //获取两个出现一次的数字异或结果 x1 ^ x2
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }

        //获取异或结果最低位 1 的位置
        int rIndex = -1;
        for (int i = 0; i < 32; i++) {
            if (((res >> i) & 1) == 1) {
                rIndex = i;
                break;
            }
        }

        //根据异或结果最低位 1 将数组分成两组，这样 x1 和 x2 就分开在两部分中
        for (int n : nums) {
            if (((n >> rIndex) & 1) == 1) {
                ret[0] ^= n;
            } else {
                ret[1] ^= n;
            }
        }

        return ret;
    }

    /**
     * 两个不相等的元素在位级表示上必定会有一位存在不同。
     * 将数组的所有元素异或得到的结果为不存在重复的两个元素异或的结果。
     * diff &= -diff 得到出 diff 最右侧不为 0 的位，也就是不存在重复的两个元素在位级表示上最右侧不同的那一位，利用这一位就可以将两个元素区分开来。
     */
    public int[] singleNumber2(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff;  // 得到最右一位
        int[] ret = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                ret[0] ^= num;
            } else {
                ret[1] ^= num;
            }
        }
        return ret;
    }
}
