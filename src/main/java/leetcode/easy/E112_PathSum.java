package leetcode.easy;

import java.util.Stack;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class E112_PathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        sum -= root.val;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    //非递归前序遍历，但改变节点值
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(), left = node.left, right = node.right;
            if (left == null && right == null) {
                if (node.val == sum) {
                    return true;
                }
            }
            if (right != null) {
                //加上父节点值
                right.val += node.val;
                stack.push(right);
            }
            if (left != null) {
                //加上父节点值
                left.val += node.val;
                stack.push(left);
            }
        }
        return false;
    }

    //非递归前序遍历，双栈，不改变节点值
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        sums.push(sum);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(), left = node.left, right = node.right;
            int s = sums.pop();
            if (left == null && right == null) {
                if (node.val == s) {
                    return true;
                }
            }
            if (right != null) {
                //加上父节点值
                stack.push(right);
                sums.push(s - node.val);
            }
            if (left != null) {
                //加上父节点值
                stack.push(left);
                sums.push(s - node.val);
            }
        }
        return false;
    }
}
