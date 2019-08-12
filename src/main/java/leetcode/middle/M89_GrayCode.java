package leetcode.middle;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * Example 1:
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * <p>
 * Example 2:
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 * A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 * Therefore, for n = 0 the gray code sequence is [0].
 * <p>
 * 二进制数转格雷码：
 * （假设以二进制为0的值做为格雷码的0）
 * G：格雷码 B：二进制码 n：正在计算的位
 * 根据格雷码的定义可得：
 * G(n) = B(n+1) XOR B(n)
 * 即
 * G(n) = B(n+1) + B(n)
 * 自低位至高位运算即可，无需考虑进位，例略。
 * <p>
 * 格雷码转二进制数
 * 由于G(n) = B(n+1) + B(n)
 * 故而B(n) = B(n+1) - G(n)
 * 自高位至低位运算即可，无需考虑借位。
 * 例： 格雷码0111，为4位数，故设二进制数自第5位至第1位分别为：0 b3 b2 b1 b0。
 * b3= 0-0 =0
 * b2=b3-1=0-1=1
 * b1=b2-1=1-1=0
 * b0=b1-1=0-1=1
 * 因此所转换为之二进制码为0101
 * <p>
 * https://zh.wikipedia.org/wiki/%E6%A0%BC%E9%9B%B7%E7%A0%81
 * https://blog.csdn.net/makuiyu/article/details/44926463
 * 镜像
 * https://blog.csdn.net/cloudox_/article/details/70670180
 */
public class M89_GrayCode {

    static List<Integer> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int[] dirs = new int[]{0, 1};

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n == 0) {
            res.add(0);
            return res;
        } else if (n == 1) {
            res.add(0);
            res.add(1);
            return res;
        }
        //递归找 n-1 结果
        List<Integer> last = grayCode(n - 1);
        //n-1 结果前面加 '0'，十进制数不变
        for (int i = 0; i < last.size(); i++) {
            res.add(last.get(i));
        }
        //倒序，前面加 '1'，1 左移 n-1 位再或上 n-1 结果
        for (int i = last.size() - 1; i >= 0; i--) {
            res.add(1 << n - 1 | last.get(i));
        }
        return res;
    }

    static int cnt = 0;

    static int findSonSeq(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                helper(s.substring(i), t, i, sb);
                break;
            }
        }
        return cnt;
    }

    private static void helper(String s, String t, int start, StringBuilder sb) {
        if (sb.length() == t.length()) {
            if (t.equals(sb.toString())) {
                cnt++;
            }
            return;
        }

        for (int j = start; j < s.length(); j++) {
            if (s.charAt(j) != t.charAt(sb.length())) {
                continue;
            }
            sb.append(s.charAt(j));
            helper(s, t, j + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        findSonSeq("abcabcabc", "abc");
        System.out.println(cnt);
    }
}
