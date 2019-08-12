package sword.ch6;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 题目应该有一个隐含条件，就是不能用额外的空间。虽然 Java 的题目输入参数为 String 类型，需要先创建一个字符数组使得空间复杂度为 O(N)，但是正确的参数类型应该和原书一样，为字符数组，并且只能使用该字符数组的空间。任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
 * 正确的解法应该是和书上一样，先旋转每个单词，再旋转整个字符串。
 */
public class P284_ReverseWordsInSentence {
    //先反转单词，再反转句子
    public String ReverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            //因为这里 j 可以为 n，会数组越界，因此先判断 j 再判断 chars[j] 是否为空格
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }

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


    //先反转句子，再反转单词
    public String ReverseSentence2(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);

        int i = 0, j = 0;
        while (j <= n) {
            //因为这里 j 可以为 n，会数组越界，因此先判断 j 再判断 chars[j] 是否为空格
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        return new String(chars);
    }

    //⽤用空格将字符串串分割为字符数组，从后往前遍历字符数组，依次追加， 注意追加空格即可.
    public String ReverseSentence3(String str) {
        if (str == null || str.trim().isEmpty())
            return str;
        StringBuilder builder = new StringBuilder();
        String[] string = str.split(" ");// 转换为字符数组，⽤用空格分割
//遍历字符数组
        for (int i = string.length - 1; i >= 0; i--) {
            //如果是最开始⼀一个字符数组，不不⽤用加空格
            if (i == 0) {
                builder.append(string[i]);
            } else {
                builder.append(string[i] + " "); //追加字符数组以及空格
            }
        }
        return builder.toString();

    }

    public static void main(String[] args) {
        new P284_ReverseWordsInSentence().ReverseSentence("I am a student.");
    }
}
