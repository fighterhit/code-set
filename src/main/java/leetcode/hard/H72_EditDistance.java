package leetcode.hard;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class H72_EditDistance {
    /*
     * https://www.bilibili.com/video/av48541010?from=search&seid=4334411204254628079
     *   D("abbc", "acc") //2
     * = D("abb", "ac") //2 在最后字符 c 上不进行操作
     * = 1 + min(D("ab", "ac"), //1 删除b
     *           D("abb", "a"), //2 添加c
     *           D("ab", "a"))  //1 将 b 替换为 c
     *
     *   D("ab", "ac")
     * = 1 + min(D("a", "ac"),  //1 删除b
     *           D("ab", "a"),  //1 添加c
     *           D("a", "a"))   //0 将 b 替换为 c
     *   DP方程：
     *   dp[i][j] = minDistance(word1[0...i-1], word2[0...j-1])
     *   dp[i][j] = 1. i if j == 0
     *              2. j if i == 0
     *              3. dp[i-1][j-1] if word1[i-1]==word2[j-1]
     *              4. min(dp[i-1][j],  //删除
     *                     dp[i][j-1],  //添加
     *                     dp[i-1][j-1] //替换
     *                     ) + 1
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        //dp[i][j] 表示从 word1 的前i个字符转换到 word2 的前j个字符所需要的步骤
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        /**
         * 当word1[i] == word2[j]时，dp[i][j] = dp[i - 1][j - 1].
         * 其他情况时，dp[i][j]是其左，左上，上的三个值中的最小值加1，其实这里的左，上，和左上，分别对应的增加，删除，修改操作
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    int[][] memo;

    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        return helper(word1, 0, word2, 0);
    }

    private int helper(String word1, int i, String word2, int j) {
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        int res = 0;
        if (word1.charAt(i) == word2.charAt(j)) {
            return helper(word1, i + 1, word2, j + 1);
        } else {
            //删除
            int delCnt = helper(word1, i + 1, word2, j);
            int addCnt = helper(word1, i, word2, j + 1);
            int replaceCnt = helper(word1, i + 1, word2, j + 1);
            res = Math.min(Math.min(delCnt, addCnt), replaceCnt) + 1;
        }
        return memo[i][j] = res;
    }
}
