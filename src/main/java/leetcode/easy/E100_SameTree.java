package leetcode.easy;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * <p>
 * Example 1:
 * Input:     1         1
 * / \       / \
 * 2   3     2   3
 * [1,2,3],   [1,2,3]
 * Output: true
 * <p>
 * Example 2:
 * Input:     1         1
 * /           \
 * 2             2
 * [1,2],     [1,null,2]
 * <p>
 * Output: false
 * <p>
 * Example 3:
 * Input:     1         1
 * / \       / \
 * 2   1     1   2
 * [1,2,1],   [1,1,2]
 * Output: false
 *
 * @author Fighter.
 * 参考 E101_SymmetricTree
 */
public class E100_SameTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        return p != null && q != null ? p.val == q.val && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right)
                : p == null && q == null;
    }

    public boolean isSameTree3(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
