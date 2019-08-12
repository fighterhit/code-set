package sword.ch2;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * >> 带符号右移； >>> 无符号右移
 *
 * @author Fighter Created on 2018/5/1.
 */
public class P100_NumberOf1InBinary {

    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            //把一个整数减去 1，再和原整数做与运算会把该整数最右边的 1 变成 0，那么一个整数二进制表示有多少个 1 就可以进行多少次这样的操作
            n &= n - 1;
        }
        return cnt;
    }

    public int NumberOf12(int n) {
        int cnt = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                cnt++;
            }
            // >> 是带符号右移，>>> 是无符号右移
            n >>>= 1;
        }
        return cnt;
    }

    //n不动，对1进行移位，32位整数就移32位

    public int NumberOf13(int n) {
        int cnt = 0, flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                cnt++;
            }
            flag <<= 1;
        }
        return cnt;
    }

    //使用一条语句判断一个正整数是不是2的整数次方(只有1位是1，其余位全为0)

    public static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

}
