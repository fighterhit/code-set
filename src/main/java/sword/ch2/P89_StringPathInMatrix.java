package sword.ch2;

/**
 * @author Fighter Created on 2018/5/4.
 */
public class P89_StringPathInMatrix {

    private int rows, cols;
    private int[][] next =  {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0){
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        String s = String.valueOf(str);

        char[][] mx = makeMatrix(matrix, rows, cols);

        //注意千万不要 Arrays.fill(visited, false); 因为visited是数组地址，visited[0]和visited[1]才存的是布尔值
        // Arrays.fill(visited[0], false); Arrays.fill(visited[1], false);
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPath(mx, s, visited, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[][] mx, String s, boolean[][] visited, int pathLen, int i, int j) {
        if (s.length() == pathLen) {
            return true;
        }
        if (i < 0  || i >= mx.length || j < 0 || j >= mx[0].length || mx[i][j] != s.charAt(pathLen) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        for (int[] ints : next) {
            if (hasPath(mx, s, visited, pathLen + 1, i + ints[0], j + ints[1])) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    private char[][] makeMatrix(char[] matrix, int rows, int cols) {
        char[][] mx = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mx[i][j] = matrix[i * cols + j];
            }
        }
        return mx;
    }

}
