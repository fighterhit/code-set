package structure.Graph.ShortestPath;

import structure.Graph.MinimumSpanTrees.*;

import java.util.Stack;
import java.util.Vector;

/**
 * @author Fighter.
 */
public class Dijkstra<Weight extends Number & Comparable> {
    //图的引用
    private WeightedGraph G;
    //起始点
    private int s;
    //distTo[i] 存储从起始点s到i的最短路径长度
    private Number[] disTo;
    //标记数组，在算法运行过程中标记到节点i的最短路径是否已被找到
    private boolean[] marked;
    //from[i]记录最短路径中，到达i点的边是哪一条
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph g, int s) {
        G = g;
        assert s >= 0 && s < G.V();
        this.s = s;
        disTo = new Number[G.V()];
        marked = new boolean[G.V()];
        from = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            disTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }

        //使用索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(G.V());

        disTo[s] = 0.0;
        from[s] = new Edge<>(s, s, (Weight) (Number) 0.0);
        ipq.insert(s, (Weight) disTo[s]);
        marked[s] = true;

        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();
            marked[v] = true;
            for (Object o : G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) o;
                int w = e.other(v);
                //若从s到w点的最短路径还没找到
                if (!marked[w]) {
                    if (from[w] == null || disTo[v].doubleValue() + e.wt().doubleValue() < disTo[w].doubleValue()) {
                        disTo[w] = disTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;
                        if (ipq.contain(w)) {
                            ipq.change(w, (Weight) disTo[w]);
                        } else {
                            ipq.insert(w, (Weight) disTo[w]);
                        }
                    }
                }
            }
        }
    }

    // 判断从s点到w点是否联通
    boolean hasPathTo(int w) {
        assert w >= 0 && w < G.V();
        return marked[w];
    }

    // 返回从s点到w点的最短路径长度
    Number shortestPathTo(int w) {
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        return disTo[w];
    }

    // 寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
    Vector<Edge<Weight>> shortestPath(int w) {
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        Stack<Edge<Weight>> s = new Stack<>();
        Edge<Weight> e = from[w];
        while (e.v() != this.s) {
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        Vector<Edge<Weight>> res = new Vector<>();
        while (!s.empty()) {
            e = s.pop();
            res.add(e);
        }
        return res;
    }

    void showPath(int w) {
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).v() + "->");
            if (i == path.size() - 1) {
                System.out.println(path.get(i).w());
            }
        }
    }

    public static void main(String[] args) {
        String filename = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\ShortestPath\\testG1.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
//        SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:\n");
        Dijkstra<Integer> dij = new Dijkstra<Integer>(g, 0);
        for (int i = 1; i < V; i++) {
            if (dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            } else {
                System.out.println("No Path to " + i);
            }
            System.out.println("----------");
        }
    }
}
