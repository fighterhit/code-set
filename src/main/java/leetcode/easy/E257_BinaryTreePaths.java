package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class E257_BinaryTreePaths {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归
    List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        helper(root, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode root, List<String> cur) {
        if (root == null) {
            return;
        }
        cur.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            res.add(String.join("->", cur));
        }
        helper(root.left, cur);
        helper(root.right, cur);
        cur.remove(cur.size() - 1);
    }

    //递归
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        if (root.left == null && root.right == null) {
            paths.add("" + root.val);
        }
        for (String s : binaryTreePaths2(root.left)) {
            paths.add(root.val + "->" + s);
        }
        for (String s : binaryTreePaths2(root.right)) {
            paths.add(root.val + "->" + s);
        }
        return paths;
    }

    //递归 最快
    public List<String> binaryTreePaths3(TreeNode root) {
        if (root != null) {
            searchBTP(root, "");
        }
        return res;
    }

    //用 String 不用 StringBuffer 可以回溯，否则回溯时需要删除后面字符串
    private void searchBTP(TreeNode root, String cur) {
        if (root.left == null && root.right == null) {
            res.add(cur + root.val);
        }
        if (root.left != null) {
            searchBTP(root.left, cur + root.val + "->");
        }
        if (root.right != null) {
            searchBTP(root.right, cur + root.val + "->");
        }
    }
}
