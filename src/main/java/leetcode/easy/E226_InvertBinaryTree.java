package leetcode.easy;

import java.util.*;

/**
 * Invert a binary tree.
 * <p>
 * Example:
 * Input:
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * <p>
 * Output:
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 *
 * @author Fighter.
 */
public class E226_InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //自顶向下交换
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //交换当前节点的左右孩子
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        //左右子树同样操作
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    //自底向上交换
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        //先保存
        TreeNode left = root.left, right = root.right;
        //先递归，交换左右子树
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    //DFS 栈
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            //交换左右孩子节点，栈 pop
            TreeNode node = stack.pop(), left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return root;
    }

    //BFS 队列 层序遍历
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //队列 poll
            TreeNode node = queue.poll(), left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }
}
