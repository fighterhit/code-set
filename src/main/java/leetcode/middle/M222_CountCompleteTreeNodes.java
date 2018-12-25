package leetcode.middle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a complete binary tree, count the number of nodes.
 * Note:
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Example:
 * Input:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * Output: 6
 *
 * @author Fighter.
 * 可以用递归和非递归两种方法来解。我们先来看递归的方法，思路是分别找出以当前节点为根节点的左子树和右子树的高度并对比，如果相等，则说明是满二叉树，直接返回节点个数，如果不相等，则节点个数为左子树的节点个数加上右子树的节点个数再加1(根节点)，其中左右子树节点个数的计算可以使用递归来计算
 */
public class M222_CountCompleteTreeNodes {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //层序遍历：Time Limit Exceeded 超时
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cnt = 0;
        //层序遍历
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(), left = node.left, right = node.right;
            cnt++;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
        return cnt;
    }

    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root, right = root;
        int leftHeight = 0, rightHeight = 0;
        while (left != null) {
            leftHeight++;
            left = left.left;
        }
        while (right != null) {
            rightHeight++;
            right = right.right;
        }
        if (leftHeight == rightHeight) {
            return 1 << leftHeight - 1;
        }
        return 1 + countNodes2(root.left) + countNodes2(root.right);
    }

    public int countNodes3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left, right = root.right;
        int num = 1;
        while (right != null) {
            left = left.left;
            right = right.left;
            num = num * 2;
        }
        //left == null right == null，左右子树高度相同，左子树必为完美（满）二叉树，只考虑右子树
        //并且满二叉树部分带上根节点总节点数 = num * 2
        if (left == null) {
            return num + countNodes3(root.right);
        } else {
            return num + countNodes3(root.left);
        }
    }
}
