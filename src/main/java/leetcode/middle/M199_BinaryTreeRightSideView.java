package leetcode.middle;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * Example:
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * 层序遍历（队列），遍历时从右往左添加下层节点
 */
public class M199_BinaryTreeRightSideView {

    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            res.add((int) queue.getFirst().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //从右往左添加
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return res;
    }
}
