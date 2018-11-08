package structure.Graph.MinimumSpanTrees;

import structure.UnionFind.UnionFind6;

import java.util.Vector;

/**
 * @author Fighter.
 */
public class KruskalMST<Weight extends Number & Comparable> {

    //最小生成树所包含所有边
    private Vector<Edge<Weight>> mst;
    //最小生成树的权值
    private Number mstWeight;

    //构造函数，使用Kruskal算法计算graph的最小生成树
    public KruskalMST(WeightedGraph graph) {
        mst = new Vector<>();
        //将图中所有边存放到一个最小堆中
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object o : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) o;
                if (e.v() <= e.w()) {
                    //通过比较边的两端点序号大小，只存一条边
                    pq.insert(e);
                }
            }
        }

        //创建一个并查集，来查看已经访问的节点的联通情况
        UnionFind6 uf = new UnionFind6(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {

            //从最小堆中依次从小到大取出所有边
            Edge<Weight> e = pq.extractMin();
            //如果该边两端点联通，说明加入这条边产生环，扔掉这条边
            if (uf.isConnected(e.v(), e.w())) {
                continue;
            }

            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        //计算最小生成树权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }

    //返回最小生成树的值
    Number result() {
        return mstWeight;
    }

    //返回最小生成树所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    public static void main(String[] args) {
        String filename = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG1.txt";
        int V = 8;
        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        ReadWeightedGraph readWeightedGraph = new ReadWeightedGraph(g, filename);
        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + kruskalMST.result());
        System.out.println();
    }

}
