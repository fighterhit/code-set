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
}
