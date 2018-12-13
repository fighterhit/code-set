package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Example:
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * @author Fighter.
 */
public class H145_BinaryTreePostorderTraversal {
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
    public List<Integer> postorderTraversal(TreeNode root) {
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
                //后序遍历则最后压入根节点，会最后访问到
                stack.push(new Command("print", command.node));
                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }
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

    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root != null) {
            postorderTraversal2(root.left);
            postorderTraversal2(root.right);
            list.add(root.val);
        }
        return list;
    }
}
