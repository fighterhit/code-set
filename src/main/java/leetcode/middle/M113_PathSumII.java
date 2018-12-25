package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * Given the below binary tree and sum = 22,
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * Return:
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 *
 * @author Fighter.
 */
public class M113_PathSumII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    //    Stack<TreeNode> tmpRes = new Stack<>();
    List<Integer> tmpRes = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        helper(root, sum);
        return res;
    }

    private void helper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            List<Integer> ls = new ArrayList<>(tmpRes);
            ls.add(root.val);
            res.add(ls);
            return;
        }
        tmpRes.add(root.val);
        helper(root.left, sum - root.val);
        helper(root.right, sum - root.val);
        //遍历完左右子树，弹出当前节点
        tmpRes.remove(tmpRes.size() - 1);
    }
}
