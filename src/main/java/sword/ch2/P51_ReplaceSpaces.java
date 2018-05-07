package sword.ch2;

/**
 * @author Fighter Created on 2018/4/18.
 */
public class P51_ReplaceSpaces {
    public static String replaceSpace(StringBuffer str) {
        //原字符串尾部
        int oldIndex = str.length() - 1;
        for (int i = 0; i <= oldIndex; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        //新字符串尾部
        int newIndex = str.length() - 1;
        // i >= 0 保证不越界；oldIndex != newIndex 保证空格都已找到，不必再往下找，若还有空格，新旧指针之间一定还有距离
        for (int i = oldIndex, j = newIndex; i >= 0 && oldIndex != newIndex; i--) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(j--,'0');
                str.setCharAt(j--,'2');
                str.setCharAt(j--,'%');
            } else {
                str.setCharAt(j--, str.charAt(i));
            }
        }

        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuffer("We Are Happy")));
    }
}
