package leetcode.hard;

/**
 * 9 * 9 大方格填 1-9 数字
 * 数独问题：同 行/列/3*3方格 内不允许重复数字
 * 参考
 */
public class H37_SudokuSolver {

    boolean[][] rowUsed = new boolean[9][10];
    boolean[][] colUsed = new boolean[9][10];
    boolean[][] cubeUsed = new boolean[9][10];
    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        //初始化布尔数组
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rowUsed[i][num] = true;
                colUsed[j][num] = true;
                cubeUsed[cubeNum(i, j)][num] = true;
            }
        }
        helper(0, 0);
    }

    /**
     * 在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
     * 但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
     */
    boolean helper(int r, int c) {
        //找到第一个是'.'的元素
        while (r < 9 && board[r][c] != '.') {
            // c == 8 时当前行遍历完
            r = (c == 8) ? r + 1 : r;
            //所有列遍历完，要跳转到下一行的第一列
            c = (c == 8) ? 0 : c + 1;
        }
        //所有行遍历完说明找到解
        if (r == 9) {
            return true;
        }
        //尝试1-9数字
        for (int i = 1; i <= 9; i++) {
            if (rowUsed[r][i] || colUsed[c][i] || cubeUsed[cubeNum(r, c)][i]) {
                continue;
            }
            rowUsed[r][i] = colUsed[c][i] = cubeUsed[cubeNum(r, c)][i] = true;
            board[r][c] = (char) (i + '0');
            if (helper(r, c)) {
                return true;
            }
            board[r][c] = '.';
            rowUsed[r][i] = colUsed[c][i] = cubeUsed[cubeNum(r, c)][i] = false;
        }
        return false;
    }

    private int cubeNum(int i, int j) {
        int r = i / 3;
        int c = j / 3;
        return 3 * r + c;
    }
}
