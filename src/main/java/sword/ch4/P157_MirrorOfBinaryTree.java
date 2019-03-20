package sword.ch4;

import sword.TreeNode;

/**
 * @author Fighter.
 */
public class P157_MirrorOfBinaryTree {
    //递归
    public void Mirror(TreeNode root) {
        //根节点为空直接返回
        if (root == null) {
            return;
        }
        //根节点不空，但左右子节点都为空，直接返回
        if (root.left == null && root.right == null) {
            return;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }

    //迭代

}
