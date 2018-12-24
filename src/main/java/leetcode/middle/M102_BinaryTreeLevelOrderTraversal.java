package leetcode.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层次遍历
 * <p>
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */

public class M102_BinaryTreeLevelOrderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        TreeNode tmp;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                tmp = queue.poll();
                integers.add(tmp.val);
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }
            res.add(integers);
        }

        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode tmp;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                //注意 poll 是移除并返问队列头部的元素，如果队列为空，则返回null
                tmp = queue.poll();
                integers.add(tmp.val);
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            res.add(integers);
        }

        return res;
    }

    //递归版：DFS
    //参考 E107_BinaryTreeLevelOrderTraversalII
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder33(res, root, 0);
        return res;
    }

    public void levelOrder33(List<List<Integer>> res, TreeNode root, int currentLevel) {
        if (root == null) {
            return;
        }
        if (currentLevel == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(currentLevel).add(root.val);
        levelOrder33(res, root.left, currentLevel + 1);
        levelOrder33(res, root.right, currentLevel + 1);
    }
}