package leetcode.easy;

import java.util.Stack;

/**
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *   3
 *  / \
 * 9  20
 *   / \
 *  15  7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 * @author Fighter.
 */
public class E404_SumofLeftLeaves {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) {
            return false;
        }
        return root.left == null && root.right == null;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root);
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        //判断左孩子是否为左叶子：当前节点非空，且其左孩子的左孩子为null，左孩子的右孩子也为null
        //不能直接return，否则无法遍历右孩子
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        return res + helper(root.left) + helper(root.right);
    }

    //非递归：栈，思想与上面判断叶子节点方法相同
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            if (node.left != null && node.left.left == null && node.left.right == null) {
                sum += node.left.val;
            }
            stack.push(node.right);
            stack.push(node.left);
        }
        return sum;
    }

    public int sumOfLeftLeaves4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, false);
    }

    int helper(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return helper(root.left, true) + helper(root.right, false);
    }
}
