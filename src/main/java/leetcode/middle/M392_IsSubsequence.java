package leetcode.middle;

/**
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * <p>
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 */
public class M392_IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        int last = 0;
        for (char c : chars) {
            int i = t.substring(last).indexOf(c);
            if (i < 0) {
                return false;
            }
            last += i + 1;
        }
        return true;
    }

    //更快
    public boolean isSubsequence2(String s, String t) {
        char[] chars = s.toCharArray();
        int index = -1;
        for (char c : chars) {
            //注意这个 API 的第二个参数
            index = t.indexOf(c, index + 1);
            if (index < 0) {
                return false;
            }
        }
        return true;
    }

    //参考 M524_LongestWordinDictionarythroughDeleting isSubstr()
    public boolean isSubsequence3(String s, String t) {
        int i = 0, j =0, m = s.length(), n = t.length();
        while(i < m && j < n){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i == m;
    }
}
