package NewCoderProblemSet.Other.alibaba.P3;

/**
 * 给定一个字符串以及多个子串，对于在字符串中出现的子串可以多次移除，求多次移除后能够得到的最短字符串长度。
 * 输入： 第一行为一个字符串，第二行为多个子串，字符串长度大于0
 * 输出： 多次移除后能够得到的最短字符串长度
 * 例如:
 * 输入:
 * "ccdaabcdbb"
 * ["ab","cd"]
 * 输出:
 * 2
 * 解释:
 * ccdaabcdbb -> ccdacdbb -> cabb -> cb (length = 2)
 * 输入:
 * "abcabd"
 * ["ab","abcd"]
 * 输出:
 * 0
 * 解释:
 * abcabd -> abcd -> "" (length = 0)
 */
public class Main {
    public static void main(String[] args) {
        String str = "ccdaabcdbb";
        String[] p = new String[]{"ab", "cd"};
//        String str = "abcabd";
//        String[] p = new String[]{"ab", "abcd"};
        System.out.println(removeStr(str, p));
    }

    public static int removeStr(String str, String[] words) {
        int len = str.length();
        int min = str.length();
        for (int i = 0; i < words.length; i++) {
            int start = 0;
            while (str.indexOf(words[i], start) >= 0) {
                int index = str.indexOf(words[i], start);

                StringBuilder sb = new StringBuilder();
                sb.append(str, 0, index);
                sb.append(str, index + words[i].length(), len);

                min = Math.min(removeStr(sb.toString(), words), min);

                start = index + words[i].length();
            }
        }
        return min;
    }
}
