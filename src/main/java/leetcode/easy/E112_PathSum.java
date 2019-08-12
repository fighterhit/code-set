package leetcode.easy;

import java.util.Stack;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 * Example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * <p>
 * https://www.cnblogs.com/grandyang/p/4036961.html
 * <p>
 * E112_PathSum 判断是否存在给定路径和路径（根节点到叶子节点）
 * M113_PathSumII 找到所有路径和等于指定值的路径，返回列表
 * E437_PathSumIII 找到所有路径和等于指定值的路径数，路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class E112_PathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 用深度优先算法DFS的思想来遍历每一条完整的路径，也就是利用递归不停找子节点的左右子节点，而调用递归函数的参数只有当前节点和sum值。
     * 首先，如果输入的是一个空节点，则直接返回false，如果如果输入的只有一个根节点，则比较当前根节点的值和参数sum值是否相同，若相同，返回true，否则false。 这个条件也是递归的终止条件。
     * 下面开始递归，由于函数的返回值是Ture/False，我们可以同时两个方向一起递归，中间用或||连接，只要有一个是True，整个结果就是True。递归左右节点时，这时候的 sum 值应该是原 sum 值减去当前节点的值。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        sum -= root.val;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * 非递归前序遍历
     * 左右子结点都需要加上其父结点值，这样当遍历到叶结点时，如果和sum相等了，那么就说明一定有一条从root过来的路径。
     * 注意这里不必一定要先处理右子结点，调换下顺序也是可以的，因为不论是先序遍历的根-左-右，还是根-右-左，并不会影响到我们找路径
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(), left = node.left, right = node.right;
            if (left == null && right == null) {
                if (node.val == sum) {
                    return true;
                }
            }
            if (right != null) {
                //加上父节点值
                right.val += node.val;
                stack.push(right);
            }
            if (left != null) {
                //加上父节点值
                left.val += node.val;
                stack.push(left);
            }
        }
        return false;
    }

    //非递归前序遍历，双栈，不改变节点值
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        sums.push(sum);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(), left = node.left, right = node.right;
            int s = sums.pop();
            if (left == null && right == null) {
                if (node.val == s) {
                    return true;
                }
            }
            if (right != null) {
                //加上父节点值
                stack.push(right);
                sums.push(s - node.val);
            }
            if (left != null) {
                //加上父节点值
                stack.push(left);
                sums.push(s - node.val);
            }
        }
        return false;
    }
}
