package leetcode.easy;

/**
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * 示例 1:
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 *  示例 4:
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 */
public class E693_BinaryNumberwithAlternatingBits {
    /**
     * 对于 1010 这种位级表示的数，把它向右移动 1 位得到 101，这两个数每个位都不同，因此异或得到的结果为 1111。
     */
    public boolean hasAlternatingBits(int n) {
        //res全1
        int res = (n ^ (n >>> 1));
        return ((res + 1) & res) == 0;
    }
}
