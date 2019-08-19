package leetcode.middle;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * Output: 1
 * <p>
 * Example 2:
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * Output: 3
 */
public class M200_NumberofIslands {

    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int xLen, yLen;

    //本质是向四个方向进行dfs
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return res;
        }
        xLen = grid.length;
        yLen = grid[0].length;
        visited = new boolean[xLen][yLen];
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int x, int y) {
        //之所以没有显式递归退出条件
        //因为保证了(x,y)合法，且grid[x][y]是没有被访问过的陆地
        visited[x][y] = true;
        for (int i = 0; i < dirs.length; i++) {
            int newX = x + dirs[i][0], newY = y + dirs[i][1];
            if (checkCoordinate(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                dfs(grid, newX, newY);
            }
        }
    }

    private boolean checkCoordinate(int x, int y) {
        return x >= 0 && x < xLen && y >= 0 && y < yLen;
    }

    int m, n;
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    dfs2(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void dfs2(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] ints : dir) {
            dfs2(grid, i + ints[0], j + ints[1]);
        }
    }
}
