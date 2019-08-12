package leetcode.middle;

public class M73_SetMatrixZeroes {
    //O(1) 空间复杂度，直接利用原数组的第一行第一列来标志某行某列是否存在0
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean rowFlag = false, colFlag = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                rowFlag = true;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    //标志该行存在0
                    matrix[i][0] = 0;
                    //标志该列存在0
                    matrix[0][j] = 0;
                }
            }
        }
        //更新数组，根据第一行第一列的标志置 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //一个元素一个元素判断
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowFlag) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colFlag) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (col[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        new M73_SetMatrixZeroes().setZeroes(new int[][]{
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}
        });
    }
}
