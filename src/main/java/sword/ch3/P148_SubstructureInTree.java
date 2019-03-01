package sword.ch3;

import sword.TreeNode;

/**
 * @author Fighter.
 */
public class P148_SubstructureInTree {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        //判断根节点是否为null
        if (root1 == null || root2 == null) {
            return false;
        }
        //当根节点都不为null再判断节点的值是否相等，根节点不同时再从左右子树开始找有没有相同子结构
        return isSubTreeWithRoot(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubTreeWithRoot(TreeNode root1, TreeNode root2) {
        //B树到达叶子节点
        if (root2 == null) {
            return true;
        }
        //A树未到叶子节点
        if (root1 == null) {
            return false;
        }
        //根节点值不同
        if (root1.val != root2.val) {
            return false;
        }
        //根节点值相同情况下看左右子树是否相同
        return isSubTreeWithRoot(root1.left, root2.left) && isSubTreeWithRoot(root1.right, root2.right);
    }
}
