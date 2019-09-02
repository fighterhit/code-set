package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1:
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 */
public class M763_PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return res;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] index = new int[26];
        //每种字符最后出现的位置
        for (int i = 0; i < n; i++) {
            index[chars[i] - 'a'] = i;
        }
        int start = 0, last = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, index[chars[i] - 'a']);
            if (last == i) {
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        return res;
    }
}
