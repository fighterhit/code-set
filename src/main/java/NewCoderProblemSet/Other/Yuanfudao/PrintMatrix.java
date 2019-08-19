package NewCoderProblemSet.Other.Yuanfudao;

import java.util.Arrays;

/**
 * 按对角线打印矩阵
 * https://www.cnblogs.com/duanxingxing/p/4928907.html
 * https://blog.csdn.net/wenniuwuren/article/details/42552753
 */
public class PrintMatrix {
    //按对角线打印矩阵，比如
    //第一行 vec[0][0]
    //第二行 vec[1][0] vec[0][1]
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
//        int[] res = printMainDiagonal(matrix, matrix.length);
        int[] res2 = printCounterDiagonal(matrix, matrix.length);
        System.out.println(Arrays.toString(res2));
    }

    //按副对角线从下往上打印，横坐标和纵坐标之和相等
    static int[] printCounterDiagonal(int[][] matrix, int n) {
        int[] res = new int[n * n];
        int sum = -1, index = 0;
        //对角线数 2 * n - 1 也可换成 m + n - 1 (m 和 n 分别表示 行数 和 列数，更通用)，参考 M498_DiagonalTraverse
        for (int i = 1; i <= 2 * n - 1; i++) {
            sum++;
            //横坐标，j=0 即每次都从第一行开始，那么每个对角线结果就是右上到左下
//            for (int j = 0; j < n; j++) {
//                int k = sum - j;
//                if (k >= 0 && k < n) {
//                    res[index++] = matrix[j][k];
//                }
//            }
            //列坐标，k=0 即每次都从第一列开始，那么每个对角线结果就是左下到右上
            for (int k = 0; k < n; k++) {
                int j = sum - k;
                if (j >= 0 && j < n) {
                    res[index++] = matrix[j][k];
                }
            }
        }
        return res;
    }

    /**
     * 二维数组按主对角线从上往下打印
     * 有一个二维数组(n*n),写程序实现从右上角到左下角沿主对角线方向打印。
     * 给定一个二位数组arr及题目中的参数n，请返回结果数组。
     * 测试样例：
     * [[1,2,3,4],
     * [5,6,7,8],
     * [9,10,11,12],
     * [13,14,15,16]],
     * 4
     * 返回：[4,3,8,2,7,12,1,6,11,16,5,10,15,9,14,13]
     * https://www.nowcoder.com/questionTerminal/6fadc1dac83a443c9434f350a5803b51
     */
    //根据每个对角线横坐标和纵坐标的差相等
    static int[] printMainDiagonal(int[][] matrix, int n) {
        int[] res = new int[n * n];
        int index = 0;
        int diff = n;
        //横纵坐标差
        //2 * n - 1 条对角线
        for (int i = 1; i <= 2 * n - 1; i++) {
            diff--;
            //横坐标
            for (int j = 0; j < n; j++) {
                //纵坐标
//                for (int k = 0; k < n; k++) {
//                    if (k - j + 1 == diff) {
//                        res[index++] = matrix[j][k];
//                    }
//                }
                int k = diff + j;
                if (k >= 0 && k < n) {
                    res[index++] = matrix[j][k];
                }
            }
        }
        return res;
    }

    static int[] printMainDiagonal2(int[][] matrix, int n) {
        int[] res = new int[n * n];
        //每个对角线起始点横纵坐标
        int startX = 0, startY = n - 1;
        int index = 0;
        while (startX < n) {
            int i = startX, j = startY;
            while (i < n && j < n) {
                res[index] = matrix[i][j];
                i++;
                j++;
                index++;
            }
            if (startY > 0) {
                startY--;
            } else {
                startX++;
            }
        }
        return res;
    }


}
