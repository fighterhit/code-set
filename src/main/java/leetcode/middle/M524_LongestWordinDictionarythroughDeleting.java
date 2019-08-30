package leetcode.middle;

import java.util.List;

/**
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 示例 1:
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 输出:
 * "apple"
 * <p>
 * 示例 2:
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 * 输出:
 * "a"
 * <p>
 * 说明:
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 * <p>
 * 题意：删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，找出能构成的最长字符串。如果有多个相同长度的结果，返回字典序的最小字符串。
 */
public class M524_LongestWordinDictionarythroughDeleting {
    /**
     * 子序列问题：
     * 通过删除字符串 s 中的一个字符能得到字符串 t，可以认为 t 是 s 的子序列，我们可以使用双指针来判断一个字符串是否为另一个字符串的子序列。
     */
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for (String t : d) {
            int resLen = res.length(), targetLen = t.length();
            //先排除一些情况：当前结果长度比要比较target串长，则target不用比
            //res长度和target长度相同，即可能会是一个结果，但字典序比res小的依然排除
            if (resLen > targetLen || (resLen == targetLen && res.compareTo(t) < 0)) {
                continue;
            }
            //那么接下来判断只要 t 是 s 的子序列就一定是一个结果
            if (isSubstr(s, t)) {
                res = t;
            }
        }
        return res;
    }

    //判断 target 是否为 s 的子序列 t
    private boolean isSubstr(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == t.length();
    }
}
