package sword.ch4;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 回溯问题
 *
 * @author Fighter.
 */
public class P182_FindPath {

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        helper(root, target, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode root, int target, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add((int) root.val);
        target -= (int) root.val;
        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        } else {
            //递归遍历左右子树
            helper(root.left, target, path);
            helper(root.right, target, path);
        }
        path.remove(path.size() - 1);
    }
}
