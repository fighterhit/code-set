package leetcode.easy;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its minimum depth = 2.
 *
 * @author Fighter.
 * 参考 E104_MaximumDepthofBinaryTree
 */
public class E111_MinimumDepthofBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //注意在只有一个孩子节点时: left + right + 1
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null || root.right == null) {
            return Math.max(minDepth2(root.left) + 1, minDepth2(root.right) + 1);
        }
        return Math.min(minDepth2(root.left) + 1, minDepth2(root.right) + 1);
    }
}
