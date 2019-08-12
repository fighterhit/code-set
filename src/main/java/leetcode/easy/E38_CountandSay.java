package leetcode.easy;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * Note: Each term of the sequence of integers will be represented as a string.
 * Example 1:
 * Input: 1
 * Output: "1"
 * <p>
 * Example 2:
 * Input: 4
 * Output: "1211"
 */
public class E38_CountandSay {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {

            StringBuilder sb = new StringBuilder();
            char[] chars = res.toCharArray();
            int len = chars.length;

            for (int j = 0; j < len; j++) {
                int cnt = 1;
                while (j + 1 < len && chars[j] == chars[j + 1]) {
                    cnt++;
                    j++;
                }
                sb.append(cnt).append(chars[j]);
            }
            res = sb.toString();
            
//            for (int k = j; k < len; ) {
//                int cnt = 0;
//                //注意这里也要先判断边界 k < len 再判断值是否相等
//                while (k < len && chars[j] == chars[k]) {
//                    cnt++;
//                    k++;
//                }
//                sb.append(cnt).append(chars[j]);
//                j = k;
//            }
//            res = sb.toString();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new E38_CountandSay().countAndSay(6));
    }
}
