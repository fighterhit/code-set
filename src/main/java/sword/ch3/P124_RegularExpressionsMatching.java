package sword.ch3;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * @author Fighter Created on 2018/5/11.
 */
public class P124_RegularExpressionsMatching {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        StringBuilder s = new StringBuilder(new String(str));
        StringBuilder p = new StringBuilder(new String(pattern));
        return matchCore(s, 0, p, 0);
    }

    private boolean matchCore(StringBuilder s, int strIndex, StringBuilder p, int patternIndex) {
        //若匹配串和模式串都匹配结束
        if (strIndex == s.length() && patternIndex == p.length()) {
            return true;
        }
        //若匹配串没结束，模式串结束
        if (strIndex != s.length() && patternIndex == p.length()) {
            return false;
        }

        if (strIndex == s.length() && patternIndex != p.length()) {
            if (patternIndex + 1 < p.length() && p.charAt(patternIndex + 1) == '*') {
                return matchCore(s, strIndex, p, patternIndex + 2);
            } else {
                return false;
            }
        }
        //如果模式串的第二个字符不是*或者已经只剩一个字符了
        if (patternIndex == p.length() - 1 || p.charAt(patternIndex + 1) != '*') {
            if (p.charAt(patternIndex) == '.' || p.charAt(patternIndex) == s.charAt(strIndex)) {
                return matchCore(s, strIndex + 1, p, patternIndex + 1);
            } else {
                return false;
            }

            //如果模式串的第二个字符是*
        } else {
            if (p.charAt(patternIndex) == '.' || p.charAt(patternIndex) == s.charAt(strIndex)) {
                return matchCore(s, strIndex + 1, p, patternIndex)
                        || matchCore(s, strIndex + 1, p, patternIndex + 2)
                        || matchCore(s, strIndex, p, patternIndex + 2);
            } else {
                return matchCore(s, strIndex, p, patternIndex + 2);
            }
        }
    }
}
