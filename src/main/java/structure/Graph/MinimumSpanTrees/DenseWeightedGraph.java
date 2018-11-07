package structure.Graph.MinimumSpanTrees;

import java.util.Vector;

/**
 * 稠密图 - 邻接矩阵
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    //节点数
    private int n;
    //边数
    private int m;
    //是否为有向图
    private boolean directed;
    //图的具体数据
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        //初始没有边
        this.m = 0;
        this.directed = directed;
        // g初始化为n*n的布尔矩阵, 每一个g[i][j]均为null, 表示没有任和边
        //todo 泛型数组
        g = (Edge<Weight>[][]) new Edge[n][n];
    }

    //返回图的节点数
    @Override
    public int V() {
        return n;
    }

    //返回图的边数
    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < n;
        assert e.w() >= 0 && e.w() < n;
        if (hasEdge(e.v(), e.w())) {
            return;
        }
        g[e.v()][e.w()] = new Edge<>(e);
        if (e.v() != e.w() && !directed) {
            g[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
        }
        m++;
    }


    // 验证图中是否有从v到w的边
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w] != null;
    }

    //打印图的信息
    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null) {
                    System.out.print(g[i][j].wt() + "\t");
                } else {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        Vector<Edge<Weight>> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != null) {
                adjV.add(g[v][i]);
            }
        }
        return adjV;
    }
}
