package leetcode.middle;

import sword.TreeNode;

import java.util.*;

/**
 * 参考 P176_printTreeInSpecial，也可以用两个栈保存从不同方向打印的元素
 */
public class M103_BinaryTreeZigzagLevelOrderTraversal {

    List<List<Integer>> res = new ArrayList<>();
    boolean flag = true;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                ls.add((Integer) n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            if (!flag) {
                Collections.reverse(ls);
            }
            res.add(ls);
            flag = !flag;
        }
        return res;
    }
}
