package structure;

public class KMP {
    int[] getNext(String p) {
        int plen = p.length();
        int j = 0;
        int k = -1;
        int[] next = new int[plen];
        next[0] = -1;
        char cj, ck;
        while (j < plen - 1) {
            if (k == -1 || (cj = p.charAt(j)) == (ck = p.charAt(k))) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    int indexOf(String s, String p) {
        int i = 0, j = 0;
        char[] chars = s.toCharArray();
        char[] charp = p.toCharArray();
        int sLen = s.length(), pLen = p.length();
        int[] next = getNext(p);
        while (i < sLen && j < pLen) {
            if (j == -1 || chars[i] == charp[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (pLen == j) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KMP().indexOf("ABCDABD", "HBCDABD"));
    }
}
