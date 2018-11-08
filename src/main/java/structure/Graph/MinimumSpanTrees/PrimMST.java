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
            //在最小索引对中存储的是点的索引，通过点的索引找到相应的边
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
                else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {
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


    public static void main(String[] args) {

        String filename1 = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG1.txt";
        int V1 = 8;

        String filename2 = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG2.txt";
        int V2 = 250;

        String filename3 = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG3.txt";
        int V3 = 1000;

        String filename4 = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG4.txt";
        int V4 = 10000;

        //String filename5 = "testG5.txt";
        //int V5 = 1000000;


        // 文件读取
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(V1, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename1);
        System.out.println( filename1 + " load successfully.");

        SparseWeightedGraph<Double> g2 = new SparseWeightedGraph<Double>(V2, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2, filename2);
        System.out.println( filename2 + " load successfully.");

        SparseWeightedGraph<Double> g3 = new SparseWeightedGraph<Double>(V3, false);
        ReadWeightedGraph readGraph3 = new ReadWeightedGraph(g3, filename3);
        System.out.println( filename3 + " load successfully.");

        SparseWeightedGraph<Double> g4 = new SparseWeightedGraph<Double>(V4, false);
        ReadWeightedGraph readGraph4 = new ReadWeightedGraph(g4, filename4);
        System.out.println( filename4 + " load successfully.");

//        SparseWeightedGraph<Double> g5 = new SparseWeightedGraph<Double>(V5, false);
//        ReadWeightedGraph readGraph5 = new ReadWeightedGraph(g5, filename5);
//        System.out.println( filename5 + " load successfully.");

        System.out.println();


        long startTime, endTime;

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST1 = new LazyPrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST2 = new LazyPrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST3 = new LazyPrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST4 = new LazyPrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

//        startTime = System.currentTimeMillis();
//        LazyPrimMST<Double> lazyPrimMST5 = new LazyPrimMST<Double>(g5);
//        endTime = System.currentTimeMillis();
//        System.out.println("Test for G5: " + (endTime-startTime) + "ms.");

        System.out.println();


        // Test Prim MST
        System.out.println("Test Prim MST:");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST1 = new PrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST2 = new PrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST3 = new PrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST4 = new PrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

//        startTime = System.currentTimeMillis();
//        PrimMST<Double> primMST5 = new PrimMST<Double>(g5);
//        endTime = System.currentTimeMillis();
//        System.out.println("Test for G5: " + (endTime-startTime) + "ms.");

        System.out.println();

    }
}
