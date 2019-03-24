package sword.ch5;

import java.util.Arrays;

public class P227_SortArrayForMinNumber {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null) {
            return null;
        }
        int len = numbers.length;
        String[] num = new String[len];
        for (int i = 0; i < len; i++) {
            num[i] = numbers[i] + "";
        }
        Arrays.sort(num, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < len; i++) {
            ret.append(num[i]);
        }
        return ret.toString();
    }
}
