package sword.ch7;

public class P318_StringToInt {
    public int StrToInt(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int len = str.length();
        boolean neg = false;
        int res = 0;
        int del = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < len; i++) {
            if (i == 0 && chars[i] < '0') {
                if (chars[i] == '-') {
                    neg = true;
                } else if (chars[i] != '+') {
                    return 0;
                }
                continue;
            }
            if (i > 0 && (chars[i] < '0' || chars[i] > '9')) {
                return 0;
            }
            del = (int) ((chars[i] - '0') * Math.pow(10, len - i - 1));
            res += del;
        }
        //注意边界：-2147483648 取 - 还是 -2147483648，不用特殊处理
        if (!neg) {
            return res;
        } else {
            return -res;
        }
    }

    public int StrToInt2(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
            // 注意整数各位数求和方法
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }

    public static void main(String[] args) {
        System.out.println(new P318_StringToInt().StrToInt("-2147483648"));
        int a = 2147483640;
        a += 8;
        System.out.println(a);
    }
}
