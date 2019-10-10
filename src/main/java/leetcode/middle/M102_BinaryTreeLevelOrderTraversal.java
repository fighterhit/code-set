package leetcode.middle;

import com.sun.tools.javac.code.Lint;

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

/**
 * 二叉树层序遍历——递归和非递归版本
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

    //二叉树层序遍历——递归版：DFS
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
        //同层第一个元素添加新空的列表后不会再添加新列表
        if (currentLevel == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(currentLevel).add(root.val);
        levelOrder33(res, root.left, currentLevel + 1);
        levelOrder33(res, root.right, currentLevel + 1);
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.max(l, r) + 1;
    }

    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        int depth = getDepth(root);
        for (int i = 1; i <= depth; i++) {
            levelOrder44(root, i);
        }
        return res;
    }

    //二叉树层序遍历——递归版：DFS
    public void levelOrder44(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            res.add(root.val);
            return;
        }
        levelOrder44(root.left, level - 1);
        levelOrder44(root.right, level - 1);
    }

    //参考
    static boolean flag = true;
    static List<Integer> res = new ArrayList<>();

    //层序遍历递归
    public static List<Integer> levelorder2(TreeNode root) {
        if (root == null) {
            return res;
        }
        int level = 1;
        while (flag) {
            flag = false;
            levelorder2helper(root, level);
            level++;
        }
        return res;
    }

    public static void levelorder2helper(TreeNode root, int level) {
        if (root == null)
            return;
        if (level == 1) {
            res.add(root.val);
            if (root.left != null || root.right != null) {
                flag = true;
            }
            return;
        }
        levelorder2helper(root.left, level - 1);
        levelorder2helper(root.right, level - 1);
    }
}