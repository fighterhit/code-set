package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

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

    //非递归
    public List<Integer> postorderTraversal(TreeNode root) {
        return null;
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
