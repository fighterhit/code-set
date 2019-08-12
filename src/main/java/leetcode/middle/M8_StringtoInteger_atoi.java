package leetcode.middle;

/**
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 * Note:
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <p>
 * Example 1:
 * Input: "42"
 * Output: 42
 * <p>
 * Example 2:
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * <p>
 * Example 3:
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * <p>
 * Example 4:
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 * 参考 P318_StringToInt
 */
public class M8_StringtoInteger_atoi {
    public int myAtoi(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }
        int i = 0, sLen = s.length();
        //找到第一个非空格字符
        while (i < sLen && s.charAt(i) == ' ') {
            i++;
        }
        boolean isNeg = s.charAt(i) == '-';
        int ret = 0;
        for (int j = i; j < sLen; j++) {
            char c = s.charAt(j);
            if (j == i && (c == '+' || c == '-')) {
                continue;
            }
            //非数字字符
            // 1. 非数字字符在数字字符后面，此时返回ret
            // 2. 起始字符为非数字字符，直接返回0，
            if (c < '0' || c > '9') {
                return ret == 0 ? 0 : isNeg ? -ret : ret;
            }
            //避免下面两种情况溢出：
            // ret > Integer.MAX_VALUE / 10 : "91283472332"
            // ret == Integer.MAX_VALUE / 10 && c > '7' : "2147483648"：
            if (ret > Integer.MAX_VALUE / 10 || (ret == Integer.MAX_VALUE / 10 && c > '7')) {
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ret = ret * 10 + (c - '0');
        }
        return isNeg ? -ret : ret;
    }

    public static void main(String[] args) {
//        new M8_StringtoInteger_atoi().myAtoi("-91283472332");
//        new M8_StringtoInteger_atoi().myAtoi("  -0012a42");
        new M8_StringtoInteger_atoi().myAtoi("2147483648");
    }
}
