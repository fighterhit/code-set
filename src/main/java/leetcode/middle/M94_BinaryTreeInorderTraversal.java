package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

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

    //非递归
    public List<Integer> inorderTraversal(TreeNode root) {

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
}
