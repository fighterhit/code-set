package leetcode.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    private void helper2(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        //最外面添加，最后删除
        tmpRes.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(tmpRes));
            //不要return
        }
        helper(root.left, sum - root.val);
        helper(root.right, sum - root.val);
        //删除当前节点，回溯
        //遍历完左右子树，弹出当前节点
        tmpRes.remove(tmpRes.size() - 1);
    }

    /**
     * 迭代，中序遍历
     * https://www.cnblogs.com/grandyang/p/4042156.html
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null, pre = null;
        int curSum = 0;
        while (stack.size() > 0 || root != null) {
            if (root != null) {
                curSum += root.val;
                stack.push(root);
                root = root.left;
            } else {
                cur = stack.peek();
                //若当前节点为叶子节点且和路径和符合要求
                if (cur.left == null && cur.right == null && curSum == sum) {
                    List<Integer> ls = new ArrayList<>();
                    for (TreeNode node : stack) {
                        ls.add(node.val);
                    }
                    res.add(ls);

                }
                //若当前节点有右孩子节点且上一个访问节点不是右孩子
                if (cur.right != null && pre != cur.right) {
                    root = cur.right;
                }
                //若当前节点无右孩子或右孩子已访问
                else {
                    pre = cur;
                    curSum -= cur.val;
                    stack.pop();
                }
            }
        }
        return res;
    }
}
