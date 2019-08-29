package leetcode.middle;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * <p>
 * 二分图定义：
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * 或
 * 如果可以用两种颜色对图中的节点进行着色，并且保证相邻的节点颜色不同，那么这个图就是二分图。
 * <p>
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 */
public class M785_IsGraphBipartite {
    /**
     * 染色法：DFS
     * 将相连的两个顶点染成不同的颜色，一旦在染的过程中发现有两连的两个顶点已经被染成相同的颜色，说明不是二分图。
     * 这里我们使用两种颜色，分别用 1 和 -1 来表示，初始时每个顶点用 0 表示未染色.
     * 遍历每一个顶点，如果该顶点未被访问过，则调用递归函数，如果返回 false，那么说明不是二分图，则直接返回 false。如果循环退出后没有返回 false，则返回 true。
     * 在递归函数中，如果当前顶点已经染色，如果该顶点的颜色和将要染的颜色相同，则返回 true，否则返回 false。如果没被染色，则将当前顶点染色，然后再遍历与该顶点相连的所有的顶点，调用递归函数，如果返回 false 了，则当前递归函数的返回 false，循环结束返回 true
     * https://www.cnblogs.com/grandyang/p/8519566.html
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        //默认为0，涂色为 1 或 -1
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !helper(i, 1, graph, colors)) {
                return false;
            }
        }
        return true;
    }

    //第 2 个参数表示该点应涂的颜色
    private boolean helper(int i, int color, int[][] graph, int[] colors) {
        if (colors[i] != 0) {
            return colors[i] == color;
        }
        colors[i] = color;
        for (int neighbor : graph[i]) {
            if (!helper(neighbor, -1 * color, graph, colors)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 迭代：BFS
     * 遍历整个顶点，如果未被染色，则先染色为1，然后使用 BFS 进行遍历，将当前顶点放入队列 queue 中，然后 while 循环 queue 不为空，取出队首元素，遍历其所有相邻的顶点，如果相邻顶点未被染色，则染成和当前顶点相反的颜色，然后把相邻顶点加入 queue 中，否则如果当前顶点和相邻顶点颜色相同，直接返回 false，循环退出后返回 true
     */
    public boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) {
                continue;
            }
            //没涂色则优先涂1
            colors[i] = 1;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (queue.size() > 0) {
                int node = queue.poll();
                //处理改点的邻居节点，涂成和当前节点相反的颜色
                for (int neighbor : graph[node]) {
                    if (colors[neighbor] == colors[node]) {
                        return false;
                    }
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -1 * colors[node];
                        //注意邻居染完色加入队列
                        queue.add(neighbor);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 并查集
     * 并查集，简单来说，就是归类，将同一集合的元素放在一起。
     * 开始遍历所有结点，若当前结点没有邻接结点，直接跳过。否则就要开始进行处理.
     * 并查集方法的核心就两步，合并跟查询。
     * 查询操作：对当前结点和其第一个邻接结点分别调用 find 函数，如果其返回值相同，则意味着其属于同一个集合了，这是不合题意的，直接返回 false。否则我们继续遍历其他的邻接结点，对于每一个新的邻接结点，我们都调用 find 函数，还是判断若返回值跟原结点的相同，return false。
     * 否则就要进行合并操作了，根据敌人的敌人就是朋友的原则，所有的邻接结点之间应该属于同一个组，因为就两个组，所以需要将他们都合并起来，合并的时候不管是用 root[parent] = y 还是 root[g[i][j]] = y 都是可以，因为不管直接跟某个结点合并，或者跟其祖宗合并，最终经过 find 函数追踪溯源都会返回相同的值，
     */
    public boolean isBipartite3(int[][] graph) {
        int n = graph.length;
        int[] root = new int[n];
        //默认每个节点根节点是自己
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int i = 0; i < graph.length; i++) {
            //注意，若当前节点没有邻居节点则直接跳过
            if (graph[i].length == 0) {
                continue;
            }
            //现找当前节点和第一个节点是否属于同一类
            int x = find(root, i), y = find(root, graph[i][0]);
            //和邻居节点同一类不合题意，直接返回 false
            if (x == y) {
                return false;
            }
            for (int j = 1; j < graph[i].length; j++) {
                int parent = find(root, graph[i][j]);
                if (x == parent) {
                    return false;
                }
                //都合并到第一个邻居节点的类中
                root[parent] = y;
            }
        }
        return true;
    }

    int find(int[] root, int i) {
        if (root[i] == i) {
            return i;
        }
        return find(root, root[i]);
    }
}
