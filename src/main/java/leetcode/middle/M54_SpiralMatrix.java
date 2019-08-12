package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺时针打印矩阵
 * P161_PrintMatrix
 */
public class M54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        int rows = matrix.length - 1, cols = matrix[0].length - 1;
        int r = 0, c = 0;
        while (r <= rows && c <= cols) {
            for (int i = c; i <= cols; i++) {
                res.add(matrix[r][i]);
            }
            for (int i = r + 1; i <= rows; i++) {
                res.add(matrix[i][cols]);
            }
            //注意最后一行往回拐是判断行是否重复
            if (r != rows) {
                for (int i = cols - 1; i >= c; i--) {
                    res.add(matrix[rows][i]);
                }
            }
            //第一列网上拐是判断列是否重复
            if (c != cols) {
                for (int i = rows - 1; i > r; i--) {
                    res.add(matrix[i][c]);
                }
            }
            r++;
            c++;
            rows--;
            cols--;
        }
        return res;
    }

    public static void main(String[] args) {
        new M54_SpiralMatrix().spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        });
    }
}
