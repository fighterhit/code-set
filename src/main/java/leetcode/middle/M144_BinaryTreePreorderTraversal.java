package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

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

    //非递归
    public List<Integer> preorderTraversal(TreeNode root) {

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
