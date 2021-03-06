package leetcode.easy;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * @author Fighter.
 * <p>
 * 参考 M102_BinaryTreeLevelOrderTraversal，思路一样，只不过利用了 list 的 add(index, e) 特性，即这里往头部插入元素
 */
public class E107_BinaryTreeLevelOrderTraversalII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //DFS？
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                levelRes.add(tmp.val);
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            res.add(0, levelRes);
        }
        return res;
    }

    //BFS
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderBottom(res, root, 0);
        return res;
    }

    public void levelOrderBottom(List<List<Integer>> res, TreeNode root, int currentLevel) {
        if (root == null) {
            return;
        }
        //判断当前level是否创建了集合，每一层对应一个集合，注意和M102不同，这里将集合添加到头部
        if (currentLevel == res.size()) {
            res.add(0, new ArrayList<>());
        }
        //从左往右递归
        res.get(res.size() - 1 - currentLevel).add(root.val);
        levelOrderBottom(res, root.left, currentLevel + 1);
        levelOrderBottom(res, root.right, currentLevel + 1);
    }
}
