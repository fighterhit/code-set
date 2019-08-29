package leetcode.middle;

/**
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 */
public class M318_MaximumProductofWordLengths {
    /**
     * 主要问题是判断两个字符串是否含相同字符，由于字符串只含有小写字符，总共 26 位，因此可以用一个 32 位的整数来存储每个字符是否出现过。
     */
    public int maxProduct(String[] words) {
        int n = words.length;
        //每个int表示每个words出现了哪些字符
        int[] chars = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                //置位，表示出现了字符 c
                chars[i] |= 1 << (c - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //判断是否有重复字符用 & 与操作，若与结果为 0，说明两字符串包含字符不同，否则相同
                if ((chars[i] & chars[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
