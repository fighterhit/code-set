package structure.Graph;

import design_pattern.Iterator.Iterator;

import java.util.Vector;

/**
 * 求无权图连通分量
 */
public class Components {

    //图的引用
    Graph G;
    //记录dfs过程中节点是否被访问
    private boolean[] visited;
    //连通分量数
    private int ccount;
    //每个节点归属的连通分量id
    private int[] id;

    public Components(Graph g) {
        G = g;
        this.visited = new boolean[g.V()];
        this.ccount = 0;
        this.id = new int[g.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
        //求图的连通分量
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]) {
                //深度优先遍历会将一个连通分量遍历完
                dfs(i);
                ccount++;
            }
        }
    }

    //图的深度优先遍历
    void dfs(int v) {
        //设置当前节点已被访问
        visited[v] = true;
        //设置当前节点所属连通分量
        id[v] = ccount;
        //遍历该节点所有邻居节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    //连通分量数
    public int count() {
        return ccount;
    }

    boolean isConnected(int v, int w) {
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        return id[v] == id[w];
    }

    public static void main(String[] args) {
        // TestG1.txt
        String filename = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG1.txt";
        SparseGraph sg1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(sg1, filename);
        Components component1 = new Components(sg1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        filename = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG2.txt";
        SparseGraph sg2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(sg2, filename);
        Components component2 = new Components(sg2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }
}
