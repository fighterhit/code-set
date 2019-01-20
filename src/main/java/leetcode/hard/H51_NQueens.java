package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * Example:
 * Input: 4
 * Output: [
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * <p>
 * 可进一步优化
 */
public class H51_NQueens {

    List<List<String>> res;
    boolean[] cols;
    boolean[] dia1, dia2;

    public List<List<String>> solveNQueens(int n) {
        res = new LinkedList<>();
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

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void solve(int n, int rowIndex, LinkedList<Integer> colIndex) {
        if (rowIndex == n) {
            res.add(generateBoard(n, colIndex));
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

    private List<String> generateBoard(int n, List<Integer> colIndex) {
        List<String> rowRes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[colIndex.get(i)] = 'Q';
            rowRes.add(new String(chars));
        }
        return rowRes;
    }
}
