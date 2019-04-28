package sword.ch6;

import sword.TreeNode;

public class P273_isBalanced {
   /* public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        if (Math.abs(l - r) > 1) {
            return false;
        }
        return helper(root.left) && helper(root.right);
    }

    int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return l > r ? l + 1 : r + 1;
    }*/

    boolean isBalance = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        heightHelper(root);
        return isBalance;
    }

    private int heightHelper(TreeNode root) {
        if (root == null || !isBalance) {
            return 0;
        }
        int l = heightHelper(root.left);
        int r = heightHelper(root.right);
        if (Math.abs(l - r) > 1) {
            isBalance = false;
        }
        return Math.max(l, r) + 1;
    }

}
