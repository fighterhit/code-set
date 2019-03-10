package sword.ch4;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P171_PrintTreeFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add((Integer) node.val);
            if (node.left != null) {
                ((LinkedList<TreeNode>) queue).add(node.left);
            }
            if (node.right != null) {
                ((LinkedList<TreeNode>) queue).add(node.right);
            }
        }
        return res;
    }
}
