package algorithm.Search;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. 计算在网格中从原点到特定点的最短路径长度
 * [[1,1,0,1],
 * [1,0,1,0],
 * [1,1,1,1],
 * [1,0,1,1]]
 * 题目描述：1 表示可以经过某个位置，求解从 (0, 0) 位置到 (tr, tc) 位置的最短路径长度。
 */
public class MinPathLength {
    int[][] grids = new int[][]{
            {1, 1, 0, 1},
            {1, 0, 1, 0},
            {1, 1, 1, 1},
            {1, 0, 1, 1},
    };

    public int minPathLength(int[][] grids, int tr, int tc) {
        final int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        ((LinkedList<int[]>) queue).add(new int[]{0, 0});
        int pathLen = 0;

        return 1;
    }
}
