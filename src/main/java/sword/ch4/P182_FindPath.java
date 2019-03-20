package sword.ch4;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Fighter.
 */
public class P182_FindPath {

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//    ArrayList<Integer> path = new ArrayList<>();
    Stack<Integer> path = new Stack<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return res;
        }
        helper(root, target);
        return res;
    }

    private void helper(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && (Integer) root.val == target) {
            ArrayList<Integer> ls = new ArrayList<>(path);
            ls.add((Integer) root.val);
            res.add(ls);
            return;
        }
        path.push((Integer) root.val);
        helper(root.left, target - (Integer) root.val);
        helper(root.right, target - (Integer) root.val);
        path.pop();
    }
}
