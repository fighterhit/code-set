package leetcode.hard;

import java.util.LinkedList;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * <p>
 * Example:
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 参考 H51_NQueens
 */
public class H52_NQueensII {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        //某列存在元素则为true
        cols = new boolean[n];
        //对角线1（与副对角线平行对角线）满足 i+j
        dia1 = new boolean[2 * n - 1];
        //对角线2（与主对角线平行对角线）满足 i-j+n-1
        dia2 = new boolean[2 * n - 1];
        solve(n, 0, new LinkedList<>());
        return res;
    }

    int res;
    boolean[] cols;
    boolean[] dia1, dia2;


    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void solve(int n, int rowIndex, LinkedList<Integer> colIndex) {
        if (rowIndex == n) {
            res++;
            return;
        }
        //遍历rowIndex行的所有列
        for (int i = 0; i < n; i++) {
            //将第index行的皇后放在第i列
            //第i列为false且不与已存在元素形成对角线
            if (!cols[i] && !dia1[rowIndex + i] && !dia2[rowIndex - i + n - 1]) {
                colIndex.add(i);
                cols[i] = true;
                dia1[rowIndex + i] = true;
                dia2[rowIndex - i + n - 1] = true;
                solve(n, rowIndex + 1, colIndex);
                //回溯，清除
                cols[i] = false;
                dia1[rowIndex + i] = false;
                dia2[rowIndex - i + n - 1] = false;
                colIndex.removeLast();
            }
        }
    }
}
