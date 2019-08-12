package leetcode.middle;

import sword.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class M105_ConstructBinaryTreefromPreorderandInorderTraversal {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length) {
            return null;
        }
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
//        return helper(pre, 0, in, 0, in.length - 1);
        return helper2(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode helper(int[] pre, int preStart, int[] in, int inStart, int inEnd) {
        //注意这种只取 preStart 方法边界条件！！！
        if (preStart > in.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int inIndex = map.get(root.val);
        root.left = helper(pre, preStart + 1, in, inStart, inIndex - 1);
        root.right = helper(pre, preStart + inIndex - inStart + 1, in, inIndex + 1, inEnd);
        return root;
    }

    public TreeNode helper2(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int inIndex = map.get(root.val);
        //preStart + 1: 左子树根节点索引 inIndex - inStart - 1: 左子树节点个数
        root.left = helper2(pre, preStart + 1, preStart + 1 + (inIndex - inStart - 1), in, inStart, inIndex - 1);
        root.right = helper2(pre, preStart + (inIndex - inStart) + 1, preEnd, in, inIndex + 1, inEnd);
        return root;
    }
}
