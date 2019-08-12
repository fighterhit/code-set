package NewCoderProblemSet.Official;

/**
 * 最长公共子串
 */
public class Vivo2018 {
    private static String longestSubString(String s1, String s2) {
        if (s1 == null || s2 == null) return null;
        int len1 = s1.length();
        int len2 = s2.length();
        int max = 0;
        int index = 0;
        int[][] array = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            if (s1.charAt(i) == s2.charAt(0))
                array[i][0] = 1;
        }
        for (int i = 0; i < len2; i++) {
            if (s1.charAt(0) == s2.charAt(i))
                array[0][i] = 1;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                    if (array[i][j] > max) {
                        max = array[i][j];
                        index = i;
                    }
                } else {
                    array[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return s1.substring(index - max + 1, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestSubString("abccade", "dgcadde"));
    }
}
