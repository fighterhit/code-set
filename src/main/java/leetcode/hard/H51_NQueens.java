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
        //思路：把递归过程设计成逐行放置
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

    char[][] board;
    boolean[] v45, v135;
    int n;
    boolean[] usedCols;
    List<String> tmp = new ArrayList<>();

    public List<List<String>> solveNQueens2(int n) {
        res = new ArrayList<>();
        board = new char[n][n];
        //关键：对角线处理！！！
        //和45度对角线（主对角线）平行的线上元素 i + j 相等，可拿来判断是否成对角线
        v45 = new boolean[2 * n + 1];
        //和135度对角线（副对角线）平行的线上元素 n - 1 - (i - j) 相等，可拿来判断是否成对角线
        v135 = new boolean[2 * n + 1];
        this.n = n;
        usedCols = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        helper(0);
        return res;
    }

    private void helper(int row) {
        if (row == n) {
/*          //和 res.add(new ArrayList<>(tmp)) 等价
            List<String> list = new ArrayList<>();
            for (char[] chars : board) {
                list.add(new String(chars));
            }
            res.add(list);
            */
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < n; i++) {
            int v45Index = row + i;
            int v135Index = n - 1 - (row - i);
            if (usedCols[i] || v45[v45Index] || v135[v135Index]) {
                continue;
            }
            usedCols[i] = true;
            v45[v45Index] = true;
            v135[v135Index] = true;
            board[row][i] = 'Q';
            tmp.add(new String(board[row]));
            helper(row + 1);
            board[row][i] = '.';
            tmp.remove(tmp.size() - 1);
            usedCols[i] = false;
            v45[v45Index] = false;
            v135[v135Index] = false;
        }
    }

    /**
     * N 皇后解的个数
     */
    public List<List<String>> solveNQueens3(int n) {
        res = new ArrayList<>();
        int[] record = new int[n];
        process(0, record, n);
        return res;
    }

    private int process(int i, int[] record, int n) {
        if (i == n) {
            //保存结果
            List<String> ls = new ArrayList<>();
            char[] arr = new char[record.length];
            for (int j = 0; j < record.length; j++) {
                Arrays.fill(arr, '.');
                arr[record[j]] = 'Q';
                ls.add(new String(arr));
            }
            res.add(ls);
            //返回1表示这是一个有效解
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                //注意累加
                res += process(i + 1, record, n);
            }
        }
        //返回结果
        return res;
    }

    private boolean isValid(int[] record, int i, int j) {
        //注意这里只与前 i 行已经放过皇后的行匹配，检查是否合法
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new H51_NQueens().solveNQueens2(4);
    }
}
