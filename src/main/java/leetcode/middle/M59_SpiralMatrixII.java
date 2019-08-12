package leetcode.middle;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * Example:
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class M59_SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int r0 = 0, r1 = n - 1, c0 = 0, c1 = n - 1;
        int cnt = 1;
        while (r0 <= r1 && c0 <= c1) {
            for (int i = c0; i <= c1; i++) {
                res[r0][i] = cnt++;
            }
            for (int i = r0 + 1; i <= r1; i++) {
                res[i][c1] = cnt++;
            }
            if (r0 != r1) {
                for (int i = c1 - 1; i >= c0; i--) {
                    res[r1][i] = cnt++;
                }
            }
            if (c0 != c1) {
                for (int i = r1 - 1; i > r0; i--) {
                    res[i][c0] = cnt++;
                }
            }
            r0++;
            c0++;
            r1--;
            c1--;
        }
        return res;
    }
}
