package structure.SegmentTree;

/**
 * 线段树数组实现
 *
 * @author Fighter Created on 2018/10/6.
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    //传入考察的整个数组
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        //将数组构建成树结构
        buildSegmentTree(0, 0, arr.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index) {
        return 2 * index + 2;
    }

    // 创建线段树
    // 在treeIndex的位置 创建 表示区间[l...r]的 线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[r];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 查询线段树
    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E ret = query(0, 0, data.length - 1, queryL, queryR);
        return ret;
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    // l、r 可和 treeIndex 封装在一起
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //注意边界
        if (queryL == l && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftIndex, l, mid, queryL, queryR);
        } else {
            E leftResult = query(leftIndex, l, mid, queryL, mid);
            E rightResult = query(rightIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    // 将index位置的值，更新为e
    public void set(int index, E e) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    public void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if (index <= mid) {
            set(leftIndex, l, mid, index, e);
        } else {
            set(rightIndex, mid + 1, r, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


    public static void main(String[] args) {
        /*
        //测试创建的线段树
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree);*/

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree);
        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}
