package leetcode.easy;

/**
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 * 3
 * / \
 * 9  20
 * / \
 * 15  7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 * @author Fighter.
 */
public class E404_SumofLeftLeaves {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) {
            return false;
        }
        return root.left == null && root.right == null;
    }

    /*public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root.left, true) + helper(root.right, false);
    }

    int helper(TreeNode root, boolean flag) {
        if (root == null) {
            return 0;
        }
        while (root.left != null) {
            root = root.left;
        }
        if (root.right == null) {
            if (flag) {
                return root.val;
            }
            return 0;
        } else {
            return helper(root.right, false);
        }
    }*/
}
