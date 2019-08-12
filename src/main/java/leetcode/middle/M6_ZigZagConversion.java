package leetcode.middle;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 * <p>
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * <p>
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * https://www.cnblogs.com/grandyang/p/4128268.html
 */
public class M6_ZigZagConversion {
    public String convert(String s, int numRows) {
        if (s == null || numRows < 2) {
            return s;
        }
        //首位两行中相邻两个元素的 index之差 跟 行数 是相关的，为 2*nRows - 2。可以按顺序找到所有的黑色元素在元字符串的位置，将他们按顺序加到新字符串里面。
        //对于红色元素出现的位置也是有规律的，每个红色元素的位置为 j + (2 * nRows - 2) - 2 * i, 其中，j为前一个黑色元素的在原字符串中的 index，i 为当前行数。
        int size = 2 * numRows - 2, sLen = s.length();
        StringBuilder res = new StringBuilder();
        //按行遍历
        for (int i = 0; i < numRows; i++) {
            //遍历每列，注意边界
            for (int j = i; j < sLen; j += size) {
                char c = s.charAt(j);
                res.append(c);
                //非首尾行外，中间每行添加完某列的元素后需要再添加一个之字列的元素
                if (i != 0 && i != numRows - 1) {
                    int tmpIndex = j + size - 2 * i;
                    //注意检查边界是否合法
                    if (tmpIndex < sLen) {
                        c = s.charAt(tmpIndex);
                        res.append(c);
                    }
                }
            }
        }
        return res.toString();
    }
}
