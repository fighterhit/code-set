package sword.ch5;

import java.util.BitSet;

public class P243_FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int FirstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        BitSet s1 = new BitSet(256);
        BitSet s2 = new BitSet(256);
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!s1.get(c) && !s2.get(c)) {
                // 0 0 -> 0 1
                s1.set(c);
            } else if (s1.get(c) && !s2.get(c)) {
                // 0 1 -> 1 1
                s2.set(c);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (s1.get(chars[i]) && !s2.get(chars[i])) {
                return i;
            }
        }
        return -1;
    }
}
