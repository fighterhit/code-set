package structure.Graph;

import java.util.Stack;
import java.util.Vector;

/**
 * 寻路算法, 寻找图graph从s点到其他点的路径
 */
public class Path {
    //图的引用
    private Graph G;
    //起始点
    private int s;
    //记录深度优先遍历过程中是否被访问
    boolean[] visited;
    // 记录路径, from[i]表示查找的路径上i的上一个节点
    private int[] from;

    //构造函数
    public Path(Graph g, int s) {
        this.G = g;
        assert s >= 0 && s < G.V();
        this.s = s;
        visited = new boolean[g.V()];
        from = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.s = s;
        //寻路算法
        dfs(s);
    }

    //图的深度优先遍历
    private void dfs(int v) {
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                visited[w] = true;
                from[w] = v;
                dfs(w);
            }
        }
    }

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

    public static void main(String[] args) {

        String filename = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g, 0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
