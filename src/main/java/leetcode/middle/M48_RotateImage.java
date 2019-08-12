package leetcode.middle;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * Example 2:
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * https://www.cnblogs.com/grandyang/p/4389572.html
 */
public class M48_RotateImage {
    public void rotate(int[][] matrix) {
        //先求矩阵转置，再翻转每一行
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //注意，只有当遍历到的元素在主对角线上方时交换，否则会重复交换
                //可在这里加判断 j > i，或每次内层循环 j 初始化为 i + 1 即可
                if (j > i) {
                    swap(matrix, i, j);
                }
            }
            reverse(matrix, i);
        }
    }

    void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    private void swap(int[][] matrix, int i, int j) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
    }

    private void reverse(int[][] matrix, int i) {
        int len = matrix[i].length;
        for (int j = 0; j < len / 2; j++) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[i][len - 1 - j];
            matrix[i][len - 1 - j] = tmp;
        }
    }
}
