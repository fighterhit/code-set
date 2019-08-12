package sword.ch5;

import java.util.Arrays;
import java.util.HashMap;

public class P236_LongestSubstringWithoutDup {
    public int longestSubStringWithoutDuplication(String str) {
        //curLen 相当于 f(n-1)
        int curLen = 0, maxLen = 0;
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - 'a';
            int preIndex = preIndexs[c];
            //不重复 或 重复字符间隔大于 curLen
            if (preIndex < 0 || i - preIndex > curLen) {
                curLen++;
            } else {
                //第i个字符上次出现在 f(n-1) 的最长字符串中
                maxLen = Math.max(curLen, maxLen);
                curLen = i - preIndex;
            }
            preIndexs[c] = i;
        }
        maxLen = Math.max(curLen, maxLen);
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new P236_LongestSubstringWithoutDup().longestSubStringWithoutDuplication("arabcacfr"));
        System.out.println(new P236_LongestSubstringWithoutDup().longestSubStringWithoutDuplication("abcdefaaa"));
    }
}
