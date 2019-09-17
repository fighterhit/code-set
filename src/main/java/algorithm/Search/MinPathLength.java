package algorithm.Search;


import java.util.LinkedList;

/**
 * 1. 计算在网格中从原点到特定点的最短路径长度
 * [[1,1,0,1],
 * [1,0,1,0],
 * [1,1,1,1],
 * [1,0,1,1]]
 * 题目描述：1 表示可以经过某个位置，求解从 (0, 0) 位置到 (tr, tc) 位置的最短路径长度。
 */
public class MinPathLength {
    static int[][] grids = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 1, 1},
    };
    final int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minPathLength(int[][] grids, int tr, int tc) {
        int m = grids.length, n = grids[0].length;
        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        int pathLen = 0;

        while (queue.size() > 0) {
            int size = queue.size();
            pathLen++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                //注意：这里直接标记为 0 表示已经访问过，没有用 boolean[] visited 数组标记
                grids[cur[0]][cur[1]] = 0;
                for (int[] ints : direction) {
                    int r = cur[0] + ints[0], c = cur[1] + ints[1];
                    if (r < 0 || r >= m || c < 0 || c >= n || grids[r][c] == 0) {
                        continue;
                    }
                    if (r == tr && c == tc) {
                        return pathLen;
                    }
                    queue.add(new int[]{r, c});
                }
            }
        }
        return -1;
    }
}
