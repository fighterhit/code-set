package leetcode.easy;

/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 * 参考 P310_AddTwoNumbers
 */
public class E371_SumofTwoIntegers {
    public int getSum(int a, int b) {
        //按位和
        int sum = 0;
        //进位
        int carry = 0;
        do {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        } while (carry != 0);
        return sum;
    }

    public int getSum2(int a, int b) {
        //按位和
        int sum = 0;
        //进位
        int carry = 0;
        while (b != 0) {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return sum;
    }

    public int getSum3(int a, int b) {
        return b == 0? a :getSum3(a ^ b, (a & b) << 1);
    }
}
