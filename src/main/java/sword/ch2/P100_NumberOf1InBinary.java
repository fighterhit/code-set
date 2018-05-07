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
