package leetcode.easy;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example 1:
 * Given the following tree [3,9,20,null,null,15,7]:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * Return true.
 * <p>
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * Return false.
 *
 * @author Fighter.
 */
public class E110_BalancedBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftH = maxHeight(root.left);
        int rightH = maxHeight(root.right);
        return Math.abs(leftH - rightH) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxHeight(node.left), maxHeight(node.right)) + 1;
    }


    boolean flag = true;
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        helper(root);
        return flag;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = helper(root.left) + 1;
        int rightH = helper(root.right) + 1;
        if (Math.abs(leftH - rightH) > 1) {
            flag = false;
            return -1;
        }
        return leftH < rightH ? rightH : leftH;
    }
}
