package leetcode.middle;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and !!! it is not connected to an 'O' on the border !!! (不与边界连接的 O 才翻转)
 * will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class M130_SurroundedRegions {

    int m, n;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return;
        }
        m = grid.length;
        n = grid[0].length;
        //把连接到边界的 O 找出来做标记
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(grid, 0, i);
            dfs(grid, m - 1, i);
        }
        //标记完后剩下的 O 即为待填充元素，N 则可以顺便恢复为 O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'N') {
                    grid[i][j] = 'O';
                } else if (grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 'O') {
            return;
        }
        grid[i][j] = 'N';
        for (int[] ints : dirs) {
            dfs(grid, i + ints[0], j + ints[1]);
        }
    }
}
