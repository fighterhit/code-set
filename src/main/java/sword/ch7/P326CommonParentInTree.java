package sword.ch7;

import sword.TreeNode;

/**
 * 二叉搜索树
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * 普通二叉树
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class P326CommonParentInTree {
    //二叉搜索树
    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    //普通二叉树
    public TreeNode<Integer> lowestCommonAncestor2(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode l = lowestCommonAncestor2(root.left, p, q);
        TreeNode r = lowestCommonAncestor2(root.right, p, q);
        //p 和 q 在 root 两侧，则 root 为 LCA
        if (l != null && r != null) {
            return root;
        }
        // p 和 q 都在左子树，且返回的 l 就是 p 或 q 其中最上面的一个
        if (l != null) {
            return l;
        }
        // p 和 q 都在右子树，且返回的 r 就是 p 或 q 其中最上面的一个
        if (r != null) {
            return r;
        }
        return null;
    }
}
