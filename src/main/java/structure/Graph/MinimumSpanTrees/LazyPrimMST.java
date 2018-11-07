package structure.Graph.MinimumSpanTrees;

import java.util.Vector;

/**
 * @author Fighter.
 */
public class LazyPrimMST<Weight extends Number & Comparable> {

    //图的引用
    private WeightedGraph<Weight> G;
    //最小堆，为了选最小边
    private MinHeap<Edge<Weight>> pq;
    //标记数组，在算法运行过程中标记节点i是否被访问
    private boolean[] marked;
    //最小生成树包含的所有边
    private Vector<Edge<Weight>> mst;
    //最小生成树权值
    private Number mstWeight;

    //构造函数，用Prim算法求图最小生成树
    public LazyPrimMST(WeightedGraph<Weight> graph) {

        G = graph;
        pq = new MinHeap<>(G.E());
        marked = new boolean[G.V()];
        mst = new Vector<>();

        //lazy prim
        visit(0);
        //若用邻接表，所有边都会进入queue为O(E)，再加上优先队列queue的insert操作O(logE)，时间复杂度为O(ElogE)，
        //若用邻接矩阵，所有结点都会进入queue为O(V*V)，稠密图下 V*V 约为 E，再加上优先队列queue的insert操作O(logE)，时间复杂度为O(ElogE)，
        while (!pq.isEmpty()) {
            //使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = pq.extractMin();

            //如果这条边两端已被访问，则扔掉这条边
            if (marked[e.v()] == marked[e.w()]) {
                continue;
            }

            //否则，这条边则应该存在在最小生成树中
            mst.add(e);

            if (!marked[e.v()]) {
                visit(e.v());
            } else {
                visit(e.w());
            }
        }

        //计算最小生成树的权值
        mstWeight = mst.get(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }

    //访问结点v
    private void visit(int v) {
        //保证每次都是未访问结点，据此判断程序有无bug
        assert !marked[v];
        marked[v] = true;

        //将和节点v 相连的 所有未访问的边 放入最小堆中
        for (Edge<Weight> e : G.adj(v)) {
            //未访问的边才放入最小堆中
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    //返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    //返回最小生成树的权值
    Number result() {
        return mstWeight;
    }
}
