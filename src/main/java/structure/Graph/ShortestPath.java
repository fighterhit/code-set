package structure.Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * BFS 求最短路径
 */
public class ShortestPath {
    private Graph G;
    //起始点
    private int s;
    private boolean[] visited;
    private int[] from;
    //距离源节点s的距离，一条边代表1
    private int[] ord;

    public ShortestPath(Graph g, int s) {
        G = g;
        assert s >= 0 && s < G.V();
        this.s = s;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        //BFS 无向图最短路径算法，从s开始广度优先遍历整张图
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    ord[w]++;
                    from[w] = v;
                }
            }
        }
    }

    // 查询从s点到w点是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < G.V();
        return visited[w];
    }

    public Vector<Integer> path(int w) {
        assert hasPath(w);
        // 通过from数组逆向查找到从s到w的路径, 存放到栈中
        Stack<Integer> stack = new Stack<>();
        while (from[w] != -1) {
            stack.push(w);
            w = from[w];
        }
        stack.push(s);
        // 从栈中依次取出元素, 获得顺序的从s到w的路径
        Vector<Integer> vector = new Vector<>();
        while (!stack.isEmpty()) {
            vector.add(stack.pop());
        }
        return vector;
    }

    public void showPath(int w) {
        assert hasPath(w);
        Vector<Integer> vector = path(w);
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.elementAt(i));
            if (i != vector.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1
    public int length(int w) {
        assert w >= 0 && w < G.V();
        return ord[w];
    }

    public static void main(String[] args) {
        String filename = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs = new Path(g, 0);
        System.out.print("DFS : ");
        dfs.showPath(6);

        ShortestPath bfs = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(6);

        System.out.println();

        filename = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG1.txt";
        SparseGraph g2 = new SparseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        g2.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs2 = new Path(g2, 0);
        System.out.print("DFS : ");
        dfs2.showPath(3);

        ShortestPath bfs2 = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(3);
    }
}
