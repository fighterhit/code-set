package NewCoderProblemSet.Other.DIDI.CampusRecruitment2021.P1;

import java.util.Scanner;

/**
 * 输入n，构造 n*n 个数的斐波那契数列，将这个数列在 n*n 的矩阵中从左上方按顺时针打印出来（斐波那契数列+顺时针打印矩阵）
 * 斐波那契数列：P74_Fibonacci
 * 顺时针打印矩阵：M54_SpiralMatrix
 */
public class Main {
    static int[] fib;

    public static void Fibonacci2(int n) {
        fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int total = n * n;
        Fibonacci2(total);
        int[][] matrix = new int[n][n];
        int rows = n - 1, cols = n - 1;
        int r = 0, c = 0;
        while (r <= rows && c <= cols) {
            for (int i = c; i <= cols; i++) {
                matrix[r][i] = fib[total--];
            }
            for (int i = r + 1; i <= rows; i++) {
                matrix[i][cols] = fib[total--];
            }
            if (r != rows) {
                for (int i = cols - 1; i >= c; i--) {
                    matrix[rows][i] = fib[total--];
                }
            }
            if (c != cols) {
                for (int i = rows - 1; i > r; i--) {
                    matrix[i][c] = fib[total--];
                }
            }
            r++;
            c++;
            rows--;
            cols--;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != n - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else
                    System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
