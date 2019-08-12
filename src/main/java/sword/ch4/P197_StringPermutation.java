package sword.ch4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Fighter.
 */
public class P197_StringPermutation {
    ArrayList<String> res = new ArrayList<>();
    boolean[] used;

    public ArrayList<String> Permutation(String str) {
        res = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return res;
        }
        used = new boolean[str.length()];
        PermutationHelper(str, 0, new StringBuffer());
        return res;
    }

    void PermutationHelper(String str, int index, StringBuffer sb) {
        //最后将结果集 res 内的重复元素排除
        if (index == str.length() && !res.contains(sb.toString())) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!used[i]) {
                sb.append(str.charAt(i));
                used[i] = true;
                PermutationHelper(str, index + 1, sb);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    //基于交换
    public ArrayList<String> Permutation2(String str) {
        if (str == null || str.isEmpty()) {
            return res;
        }
        PermutationHelper2(str.toCharArray(), 0);
        Collections.sort(res);
        return res;
    }

    void PermutationHelper2(char[] str, int index) {
        if (index == str.length) {
            String val = String.valueOf(str);
            if (!res.contains(val)) {
                res.add(new String(str));
            }
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                PermutationHelper2(str, index + 1);
                swap(str, index, i);
            }
        }
    }

    void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

    public static void main(String[] args) {
        List<String> ls = new P197_StringPermutation().Permutation("aa");
        System.out.println(ls.toString());
    }
}
