package leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its minimum depth = 2.
 *
 * @author Fighter.
 * 参考 E104_MaximumDepthofBinaryTree
 */
public class E111_MinimumDepthofBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //注意在只有一个孩子节点时: left + right + 1
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 二叉树的经典问题之最小深度问题就是就最短路径的节点个数，还是用深度优先搜索DFS来完成，万能的递归啊。
     * 首先判空，若当前结点不存在，直接返回0。然后看若左子结点不存在，那么对右子结点调用递归函数，并加1返回。反之，若右子结点不存在，那么对左子结点调用递归函数，并加1返回。若左右子结点都存在，则分别对左右子结点调用递归函数，将二者中的较小值加1返回即可
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth2(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth2(root.left) + 1;
        }
        return Math.min(minDepth2(root.left) + 1, minDepth2(root.right) + 1);
    }

    /**
     * 迭代
     * 层序遍历，遍历到第一个叶子节点即可返回当前层数
     */
    public int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cnt = 0;
        while (queue.size() > 0) {
            cnt++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return cnt;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return cnt;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        return helper(root, sum);
    }

    boolean helper(TreeNode root, int target){
        if(root.left == null && root.right == null){
            if(target == root.val){
                return true;
            }
            return false;
        }
        if(root.left != null){
            return helper(root.left, target - root.val);
        }
        if(root.right != null){
            return helper(root.right, target - root.val);
        }
        return helper(root.left, target - root.val) ||  helper(root.right, target - root.val);
    }
}
