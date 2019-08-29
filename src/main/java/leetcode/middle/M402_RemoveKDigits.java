package leetcode.middle;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * <p>
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * <p>
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 用 StringBuilder 维护一个单调递增栈
 * https://www.cnblogs.com/grandyang/p/5883736.html
 */
public class M402_RemoveKDigits {
    public String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            int n = c - '0';
            while (k > 0 && sb.length() > 0 && (sb.charAt(sb.length() - 1) - '0') > n) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            //提前判断避免出现前导0：若 sb 不为空 或者 当前数字n 不为 0，再加入
            if (sb.length() > 0 || n != 0) {
                sb.append(n);
            }
        }
        //若当前 k 能大于0，则从栈后弹出 k 个数字
        while (k > 0 && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
//        new M402_RemoveKDigits().removeKdigits("10", 2);
        new M402_RemoveKDigits().removeKdigits("112", 1);
    }
}
