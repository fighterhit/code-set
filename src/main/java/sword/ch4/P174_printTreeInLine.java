package sword.ch4;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P174_printTreeInLine {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).add(pRoot);
        ArrayList<Integer> lineRes = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            lineRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                lineRes.add((Integer) node.val);
                if (node.left != null) {
                    ((LinkedList<TreeNode>) queue).add(node.left);
                }
                if (node.right != null) {
                    ((LinkedList<TreeNode>) queue).add(node.right);
                }
            }
            res.add(lineRes);
        }
        return res;
    }
}
