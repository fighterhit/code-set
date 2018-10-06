package leetcode.middle;

/**
 * @author Fighter Created on 2018/10/6.
 */
public class M307_RangeSumQueryMutable {

}

class NumArray {

    SegmentTree<Integer> segmentTree;
    Integer[] arr;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            arr = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(arr, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("exception");
        }
        return segmentTree.query(i, j);
    }

    public void update(int i, int val) {
        segmentTree.set(i, val);
    }

    class SegmentTree<E> {

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
            set(0, 0, data.length, index, e);
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
    }

    interface Merger<E> {
        E merge(E a, E b);
    }
}


/*
//子数组和
class NumArray {

    //sum[i]存储nums[0...i-1]的和，sum(i, j) = sum[j] - sum[i - 1]
    int[] sum;
    int[] data;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            data = new int[nums.length];
            System.arraycopy(nums, 0, data, 0, nums.length);

            sum = new int[data.length];
            sum[0] = data[0];

            for (int i = 1; i < data.length; i++) {
                sum[i] = sum[i - 1] + data[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (sum == null) {
            throw new IllegalArgumentException("exception");
        }
        if (i == 0) {
            return sum[j];
        }
        return sum[j] - sum[i - 1];
    }

    public void update(int i, int val) {
        if (i < 0 || i >= data.length) {
            throw new IllegalArgumentException("exception");
        }

        data[i] = val;
        //若改变索引为0处的值
        if (i == 0) {
            sum[0] = data[0];
        }
        //sum[1...n-1]都重新计算
        for (int j = 1; j < data.length; j++) {
            sum[j] = sum[j - 1] + data[j];
        }
    }

    public static void main(String[] args) {
        new NumArray(new int[]{1, 3, 5}).sumRange(0, 2);
    }
}
*/


