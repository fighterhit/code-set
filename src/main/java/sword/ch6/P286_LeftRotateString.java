package sword.ch6;

public class P286_LeftRotateString {

    public String LeftRotateString(String str, int n) {
        if (str == null || n < 0) {
            return null;
        }
        //str 为空串 ""，则返回空串 ""
        if (str.isEmpty()) {
            return str;
        }
        char[] chars = str.toCharArray();
        //先翻转前 n 位
        reverse(chars, 0, n - 1);
        //再翻转 n 位后的子串
        reverse(chars, n, chars.length - 1);
        //最后整个翻转
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    //翻转字符串
    private void reverse(char[] c, int i, int j) {
        while (i < j) {
            swap(c, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }

    public String LeftRotateString2(String str, int n) {
        if (str == null || n < 0) {
            return null;
        }
        //str 为空串 ""，则返回空串 ""
        if (str.isEmpty()) {
            return str;
        }
        //注意 substring(i, j) 左闭右开[i, j)
        StringBuilder sb = new StringBuilder(str.substring(n)).append(str.substring(0, n));
        return sb.toString();
    }
}
