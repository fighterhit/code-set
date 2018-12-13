package leetcode.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * @author Fighter.
 */
public class M144_BinaryTreePreorderTraversal {
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

    //非递归前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
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
                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
                //前序遍历则最后压入根节点，会最先访问到
                stack.push(new Command("print", command.node));
            }
        }
        return res;
    }

    //递归
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal2(root.left);
            preorderTraversal2(root.right);
        }
        return list;
    }
}
