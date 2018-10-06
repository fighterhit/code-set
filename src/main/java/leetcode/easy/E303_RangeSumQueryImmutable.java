package leetcode.easy;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 *
 * @author Fighter Created on 2018/10/6.
 */
public class E303_RangeSumQueryImmutable {


}



/*
子数组和
class NumArray2 {

    //sum[i]存储nums[0...i-1]的和，sum(i, j) = sum[j] - sum[i - 1]
    int[] sum;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            sum = new int[nums.length];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i];
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

    public static void main(String[] args) {
        new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 2);
    }
}*/

//使用自定义的线段树解决
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
