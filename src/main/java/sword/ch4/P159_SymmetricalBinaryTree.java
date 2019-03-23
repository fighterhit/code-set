package sword.ch4;

import sword.TreeNode;

/**
 * 判断二叉树是否为对称二叉树：判断左右孩子节点值是否相等
 *
 * @author Fighter.
 */
public class P159_SymmetricalBinaryTree {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetricalHelper(pRoot.left, pRoot.right);
    }

    boolean isSymmetricalHelper(TreeNode lRoot, TreeNode rRoot) {
        if (lRoot == null && rRoot == null) {
            return true;
        }
        if (lRoot == null || rRoot == null) {
            return false;
        }
        if (lRoot.val != rRoot.val) {
            return false;
        }
        return isSymmetricalHelper(lRoot.left, rRoot.right) && isSymmetricalHelper(lRoot.right, rRoot.left);
    }
}
