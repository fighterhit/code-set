package leetcode.easy;

import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 *
 * @author Fighter.
 * 参考 E100_SameTree
 */
public class E101_SymmetricTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归1：找左子树的对称树再与右子树比较
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode newLeft = invertTree(root.left);
        return isSameTree(newLeft, root.right);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        return p != null && q != null ? p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
                : p == null && q == null;
    }

    private TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    //递归2：
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode p, TreeNode q) {
        return p != null && q != null ? p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left) : p == null && q == null;
    }

    //非递归
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if (right == null && left == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
}
