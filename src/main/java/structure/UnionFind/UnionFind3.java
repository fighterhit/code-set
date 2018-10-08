package structure.UnionFind;

/**
 * 基于树大小size优化：小树挂到大树下，避免长度过长
 */
public class UnionFind3 implements UF {

    private int[] id;
    //sz[i]表示以i为根的集合中元素个数
    private int[] size;

    public UnionFind3(int size) {
        this.id = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            this.id[i] = i;
            this.size[i] = 1;
        }
    }

    //返回根节点 O(h)
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }else{
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
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
