package leetcode.easy;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * Return 3. The paths that sum to 8 are:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * @author Fighter.
 * <p>
 * 每个点都当作根节点从上到下遍历一次求和
 */
public class E437_PathSumIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归 O(N^2)?
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFromNode(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFromNode(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        if (root.val == sum) {
            ret++;
        }
        ret += pathSumFromNode(root.left, sum - root.val) + pathSumFromNode(root.right, sum - root.val);
        return ret;
    }

    //O(N)
    Map<Integer, Integer> preSum = new HashMap<>();

    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        preSum.put(0, 1);
        return helper(root, 0, sum);
    }

    public int helper(TreeNode root, int curSum, int target) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        int count = preSum.getOrDefault(curSum - target, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);

        count += helper(root.left, curSum, target) + helper(root.right, curSum, target);
        preSum.put(curSum, preSum.get(curSum) - 1);
        return count;
    }
}
