package sword.ch6;

import sword.TreeNode;

public class P271_TreeDepth {
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root);
    }
    //递归
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = helper(root.left);
        int r = helper(root.right);
        return l > r ? l + 1 : r + 1;
    }
}
