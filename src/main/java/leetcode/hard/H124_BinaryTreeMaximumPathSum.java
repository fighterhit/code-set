package leetcode.hard;

import sword.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * 二叉树最大路径和：从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */
public class H124_BinaryTreeMaximumPathSum {

    /**
     * 对于每个结点来说，要知道经过其左子结点的 path 之和大还是经过右子节点的 path 之和大。那么我们的递归函数返回值就可以定义为以当前结点为根结点，到叶节点的最大路径之和，然后全局路径最大值放在参数中，用结果 res 来表示。
     * 在递归函数中，如果当前结点不存在，那么直接返回0。否则就分别对其左右子节点调用递归函数，由于路径和有可能为负数，而我们当然不希望加上负的路径和，所以我们和0相比，取较大的那个，就是要么不加，加就要加正数。
     * 然后更新全局最大值结果 res，就是以左子结点为终点的最大 path 之和加上以右子结点为终点的最大 path 之和，还要加上当前结点值，这样就组成了一个条完整的路径。
     * 而我们返回值是取 left 和 right 中的较大值加上当前结点值，因为我们 返回值的定义 是以当前结点为终点的 path 之和，所以只能取 left 和 right 中较大的那个值，而不是两个都要
     * https://www.cnblogs.com/grandyang/p/4280120.html
     */
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }

    //返回值的定义是以当前结点为终点的 path 之和
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //对于每个节点，求出其左右子树经过它的的值，即左右子树最大值加当前节点值
        int l = Math.max(0, dfs(root.left));
        int r = Math.max(0, dfs(root.right));
        //对于最终结果 res
        res = Math.max(res, l + r + (int) root.val);
        //对于当前节点
        return Math.max(l, r) + (int) root.val;
    }

    public static void main(String[] args) {
        new H124_BinaryTreeMaximumPathSum().maxPathSum(new TreeNode(1));
    }
}
