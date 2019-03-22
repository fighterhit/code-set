package sword.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fighter.
 */
public class P199_StringCombination {

    static List<char[]> ret;
    static StringBuilder sb;

    public static List<char[]> combination(char[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs);
        ret = new ArrayList<>();
        sb = new StringBuilder();
        combinationCore(strs, 0);
        return ret;
    }

    private static void combinationCore(char[] strs, int pos) {
        if (pos == strs.length) {
            ret.add(sb.toString().toCharArray());
            return;
        }
        for (int i = pos; i < strs.length; i++) {
            sb.append(strs[i]);
            //和 P197_StringPermutation 区别，这里是 i+1
            combinationCore(strs, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
//        char[] strs = {'a', 'b', 'c'};
//        List<char[]> ret = combination(strs);
//        for (char[] item : ret) {
//            for (int i = 0; i < item.length; i++)
//                System.out.print(item[i]);
//            System.out.println();
//        }
//        System.out.println();
        char[] strs2 = {'a', 'a', 'b'};
        List<char[]> ret2 = combination(strs2);
        for (char[] item : ret2) {
            for (int i = 0; i < item.length; i++) {
                System.out.print(item[i]);
            }
            System.out.println();
        }
    }
}
