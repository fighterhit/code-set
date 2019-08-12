package leetcode.middle;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * 数独游戏
 */
public class M36_ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int m = board.length, n = board[0].length;
        boolean[][] row = new boolean[m][n];
        boolean[][] col = new boolean[m][n];
        boolean[][] box = new boolean[m][n];
        int c;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //跳过 '.'
                if (board[i][j] >= '1' && board[i][j] <= '9') {
                    c = board[i][j] - '1';
                    //3 * (i / 3) + j / 3 : 第几个box
                    if (row[c][j] || col[i][c] || box[3 * (i / 3) + j / 3][c]) {
                        return false;
                    }
                    row[c][j] = col[i][c] = box[3 * (i / 3) + j / 3][c] = true;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int m = board.length, n = board[0].length;
        boolean[] used;

        //检查行
        for (int i = 0; i < m; i++) {
            used = new boolean[9];
            for (int j = 0; j < n; j++) {
                if (isDuplicate(board[i][j], used)) {
                    return false;
                }
            }
        }

        //检查列
        for (int i = 0; i < m; i++) {
            used = new boolean[9];
            for (int j = 0; j < n; j++) {
                if (isDuplicate(board[j][i], used)) {
                    return false;
                }
            }
        }

        //检查box：一个盒子一个盒子检查
        for (int i = 0; i < m; i += 3) {
            for (int j = 0; j < n; j += 3) {
                if (isDuplicateBox(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDuplicateBox(char[][] board, int x, int y) {
        boolean[] used = new boolean[9];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (isDuplicate(board[i][j], used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicate(char c, boolean[] used) {
        if (c == '.') {
            return false;
        } else if (used[c - '1']) {
            return true;
        } else {
            used[c - '1'] = true;
            return false;
        }
    }
}
