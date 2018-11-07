package structure.Graph.MinimumSpanTrees;

import java.util.Vector;

/**
 * 优化 LazyPrimMST：只维护和节点相连最短的横切边
 *
 * @author Fighter.
 */
public class PrimMST<Weight extends Number & Comparable> {

    //图的引用
    private WeightedGraph G;
    //最小索引堆
    private IndexMinHeap<Weight> ipq;
    //访问的点对应的边
    private Edge<Weight>[] edgeTo;
    //标记数组，在算法运行过程中标记节点i是否被访问
    private boolean[] marked;
    //最小生成树所包含的所有边
    private Vector<Edge<Weight>> mst;
    //最小生成树的权值
    private Number mstWeight;

    public PrimMST(WeightedGraph g) {
        G = g;
        assert G.E() >= 1;
        ipq = new IndexMinHeap<>(G.V());

        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];

        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }

        mst = new Vector<>();

        //Prim
        visit(0);
        while (!ipq.isEmpty()) {
            //用最小索引堆找出已访问边中权值最小的边
            //在最小索引对中存储的是点的索引
            int v = ipq.extractMinIndex();
            assert edgeTo != null;
            mst.add(edgeTo[v]);
            visit(v);
        }

        mstWeight = mst.elementAt(0).wt().doubleValue();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mst.get(i).wt().doubleValue() + mstWeight.doubleValue();
        }

    }

    //访问结点v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;

        for (Object adj : G.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) adj;
            int w = e.w();
            //若另一点还未被加入最小生成树中
            if (!marked[w]) {
                //若还没考察过该边，则将该节点和边加入索引堆
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                }
                //若曾考虑过该节点，但目前边更短，则进行替换
                else if (e.wt().compareTo(edgeTo[w]) < 0) {
                    edgeTo[w] = e;
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    Number result() {
        return mstWeight;
    }
}
