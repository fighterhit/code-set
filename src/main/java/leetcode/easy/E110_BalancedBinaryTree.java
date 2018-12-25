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
 * 参考 E104_MaximumDepthofBinaryTree
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

    //O(N^2)：每个节点都要遍历左右子树O(N)
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

    //O(N)：每个节点访问一次
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

    public boolean isBalanced4(TreeNode root) {
        if (root == null) {
            return true;
        }
        maxDepth(root);
        return flag;
    }

    //求高度过程中顺便判断每个节点是否平衡，仍需遍历所有节点
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        //比求节点高度多的判断条件
        if (Math.abs(leftHeight - rightHeight) > 1) {
            flag = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //高度从0开始，-1作为标志，速度更快
    public boolean isBalanced5(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper2(root) != -1;
    }

    private int helper2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper2(root.left);
        int right = helper2(root.right);
        //比求节点高度多的判断条件
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
