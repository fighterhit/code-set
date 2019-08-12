package leetcode.easy;

import java.util.Arrays;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * Input: "Hello World"
 * Output: 5
 */
public class E58_LengthofLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        //split(" ") 不会忽略字符串最前面空格，但会忽略字符串最后面空格
        String[] str = s.split("\\W+");
        return str[str.length - 1].length();
    }

    public int lengthOfLastWord2(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    /**
     * 只关于最后一个单词的长度，所以开头有多少个空格起始并不在意，从字符串末尾开始，先将末尾的空格都去掉，然后开始找非空格的字符的长度即可
     */
    public int lengthOfLastWord3(String s) {
        int right = s.length() - 1, res = 0;
        while (right >= 0 && s.charAt(right) == ' ') {
            --right;
        }
        while (right >= 0 && s.charAt(right) != ' ') {
            --right;
            ++res;
        }
        return res;
    }

    /**
     * 先对输入字符串做预处理，去掉开头和结尾的空格，然后用一个计数器来累计非空格的字符串的长度，遇到空格则将计数器清零
     */
    public int lengthOfLastWord4(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        int count = 0;
        while (s.charAt(left) == ' ') {
            ++left;
        }
        while (s.charAt(right) == ' ') {
            --right;
        }
        for (int i = left; i <= right; ++i) {
            if (s.charAt(i) == ' ') {
                count = 0;
            } else ++count;
        }
        return count;
    }

    /**
     * 不用上面那么复杂的，我们关心的主要是非空格的字符，那么我们实际上在遍历字符串的时候，如果遇到非空格的字符，我们只需要判断其前面一个位置的字符是否为空格.
     * 如果是的话，那么当前肯定是一个新词的开始，将计数器重置为1.如果不是的话，说明正在统计一个词的长度，计数器自增1即可。
     * 但是需要注意的是，当i=0的时候，无法访问前一个字符，所以这种情况要特别判断一下，归为计数器自增1那类。
     */
    public int lengthOfLastWord5(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                if (i != 0 && s.charAt(i - 1) == ' ') res = 1;
                else ++res;
            }
        }
        return res;
    }


}
