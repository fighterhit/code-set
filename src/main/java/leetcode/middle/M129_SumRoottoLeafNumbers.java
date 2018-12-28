package leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 * Example:
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 *
 * 参考 E257_BinaryTreePaths
 */
public class M129_SumRoottoLeafNumbers {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<String> nums = new ArrayList<>();

    //字符串再转数字，慢
    public int sumNumbers(TreeNode root) {
        if (root != null) {
            helper(root, "");
        }
        return nums.stream().mapToInt(Integer::valueOf).sum();
    }

    private void helper(TreeNode root, String cur) {
        if (root.left == null && root.right == null) {
            nums.add(cur + root.val);
        }
        if (root.left != null) {
            helper(root.left, cur + root.val);
        }
        if (root.right != null) {
            helper(root.right, cur + root.val);
        }
    }

    //直接存数字， 每次递归将当前存的数值*10
    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sum(root, 0);
    }

    private int sum(TreeNode root, int s) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return s * 10 + root.val;
        }
        return sum(root.left, s * 10 + root.val) + sum(root.right, s * 10 + root.val);
    }
}
