package leetcode.middle;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * Example:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class M79_WordSearch {

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int xLen, yLen;

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }

        xLen = board.length;
        yLen = board[0].length;
        visited = new boolean[xLen][yLen];
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                //换个开始位置找 word
                if (searchWord(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, char[] wordArr, int x, int y, int wordIndex) {
        //退出条件：若字符串遍历到最后一个位置
        if (wordIndex == wordArr.length - 1) {
            return board[x][y] == wordArr[wordIndex];
        }

        if (board[x][y] == wordArr[wordIndex]) {
            visited[x][y] = true;
            //当前位置匹配，则看周围四个方向
            for (int i = 0; i < dirs.length; i++) {
                int newX = dirs[i][0] + x, newY = dirs[i][1] + y;
                //判断坐标合法性、是否访问过，并且是否匹配
                if (checkCoordinate(newX, newY)
                        && !visited[newX][newY]
                        && searchWord(board, wordArr, newX, newY, wordIndex + 1)) {
                    return true;
                }
            }
            //四个位置没有匹配的
            visited[x][y] = false;
        }

        return false;
    }

    private boolean checkCoordinate(int x, int y) {
        return x >= 0 && x < xLen && y >= 0 && y < yLen;
    }


    boolean[][] v;
    //    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int m, n;

    public boolean exist2(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        v = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean helper(char[][] board, int r, int c, int index, String w) {
        if (index == w.length()) {
            return true;
        }
        //注意判断当前字符是否被访问过，是否和 w 字符串 index 位上字符相等
        if (r < 0 || r >= m || c < 0 || c >= n || v[r][c] || board[r][c] != w.charAt(index)) {
            return false;
        }

        v[r][c] = true;
        for (int[] dir : dirs) {
            if (helper(board, dir[0] + r, c + dir[1], index + 1, w)) {
                return true;
            }
        }
        v[r][c] = false;
        return false;
    }
}
