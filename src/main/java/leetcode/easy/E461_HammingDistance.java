package leetcode.easy;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class E461_HammingDistance {
    /**
     * 对两个数进行异或操作，位级表示不同的那一位为 1，统计有多少个 1 即可。
     */
    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int cnt = 0;
        /*
        //注意：移位最多移 31 位即可
        for (int i = 0; i < 32; i++) {
            if ((res & 1) != 0) {
                cnt++;
            }
            res >>>= 1;
        }*/
        //或
        while (res != 0) {
            if ((res & 1) != 0) {
                cnt++;
            }
            res >>>= 1;
        }
        return cnt;
    }

    /**
     * 使用 z&(z-1) 去除 res 位级表示最低的那一位。
     */
    public int hammingDistance2(int x, int y) {
        int res = x ^ y, cnt = 0;
        while (res != 0) {
            cnt++;
            res = res & (res - 1);

        }
        return cnt;
    }

    public int hammingDistance3(int x, int y) {
        int res = x ^ y;
        return Integer.bitCount(res);
    }
}
