package sword.ch4;

import java.util.ArrayList;

public class P161_PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r0 = 0, r1 = matrix.length - 1, c0 = 0, c1 = matrix[0].length - 1;
        while (r0 <= r1 && c0 <= c1) {
            //上
            for (int i = c0; i <= c1; i++) {
                ret.add(matrix[r0][i]);
            }
            //右
            for (int i = r0 + 1; i <= r1; i++) {
                ret.add(matrix[i][c1]);
            }
            //下：确保不同行，不重复打
            if (r0 != r1) {
                for (int i = c1 - 1; i >= c0; i--) {
                    ret.add(matrix[r1][i]);
                }
            }
            //左：注意边界
            if (c0 != c1) {
                for (int i = r1 - 1; i > r0; i--) {
                    ret.add(matrix[i][c0]);
                }
            }
            r0++;
            r1--;
            c0++;
            c1--;
        }
        return ret;
    }

    public static ArrayList<Integer> printMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        helper(matrix, 0, 0, m, n);
        return null;
    }

    static void helper(int[][] matrix, int startX, int startY, int endX, int endY) {
        if (startX >= endX || startY >= endY) {
            return;
        }
        for (int i = startY; i < endY; i++) {
            System.out.println(matrix[startX][i]);
        }
        if (startX == endX - 1) {
            return;
        }
        for (int i = startX + 1; i < endX; i++) {
            System.out.println(matrix[i][endY - 1]);
        }
        for (int i = endY - 2; i >= startY; i--) {
            System.out.println(matrix[endX - 1][i]);
        }
        if (startY == endY - 1) {
            return;
        }
        for (int i = endX - 2; i > startX; i--) {
            System.out.println(matrix[i][startY]);
        }
        helper(matrix, startX + 1, startY + 1, endX - 1, endY - 1);
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        printMatrix2(m);
    }
}
