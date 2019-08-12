package leetcode.hard;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * Example:
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class H132_PalindromePartitioningII {
    /**
     * 求最小切分数，非常明显的动规暗示。
     * 由问题出发可建立状态f[i] 表示到索引i 处时需要的最少切割数(即切割前 i 个字符组成的字符串)：
     * 状态转移方程为f[i] = min{1 + f[j]}, where j < i and substring [j, i] is palindrome
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        //[i...j]是否为回文串
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
            }
        }
        int[] cut = new int[n];
        for (int i = 0; i < n; i++) {
            cut[i] = i;
            for (int j = 0; j <= i; j++) {
                if (dp[j][i]) {
                    cut[i] = j == 0 ? 0 : Math.min(cut[i], cut[j - 1] + 1);
                }
            }
        }
        return cut[n - 1];
    }

    public static void main(String[] args) {
        new H132_PalindromePartitioningII().minCut("AAB");
    }
}
