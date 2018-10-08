package structure.UnionFind;

/**
 * 基于树实现，相同集合的元素指向同一个根
 */
public class UnionFind2 implements UF {

    private int[] id;

    public UnionFind2(int size) {
        this.id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    //返回根节点 O(h)
    private int find(int p) {
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //O(h)
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[p] = qRoot;
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
