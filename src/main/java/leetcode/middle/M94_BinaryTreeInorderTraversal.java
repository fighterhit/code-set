package leetcode.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * @author Fighter.
 */
public class M94_BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Command {
        // go, print
        String cmd;
        TreeNode node;

        public Command(String cmd, TreeNode node) {
            this.cmd = cmd;
            this.node = node;
        }
    }

    //非递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if (command.cmd.equals("print")) {
                res.add(command.node.val);
            } else {
                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }
                stack.push(new Command("print", command.node));
                //中序遍历则最后压入左子节点，会最先访问到
                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
            }
        }
        return res;
    }

    //递归
    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root != null) {
            inorderTraversal2(root.left);
            list.add(root.val);
            inorderTraversal2(root.right);
        }
        return list;
    }

    //非递归：栈，出栈顺序即中序遍历顺序，注意这个结构，M94_BinaryTreeInorderTraversal / M230_KthSmallestElementinaBST / M98_ValidateBinarySearchTree
    //https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
