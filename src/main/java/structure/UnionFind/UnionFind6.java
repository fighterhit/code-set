package structure.UnionFind;

/**
 * 递归版路径压缩
 */
public class UnionFind6 implements UF {

    private int[] id;
    // rank[i]表示以i为根的集合所表示的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
    private int[] rank;

    public UnionFind6(int size) {
        this.id = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.id[i] = i;
            this.rank[i] = 1;
        }
    }

    //返回根节点 O(h)
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        //在找根节点过程中顺便压缩，递归到根节点返回，从而将所有点父节点设为根节点实现压缩
        if (id[p] != p) {
            id[p] = find(id[p]);
        }
        //注意返回的是id[p] 而不是 p
        return id[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            id[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            id[qRoot] = pRoot;
        } else {
            //注意：高度相等的特殊情况
            id[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }
}



