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
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * Return 3. The paths that sum to 8 are:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * @author Fighter.
 * 1.递归 每个点都当作根节点从上到下遍历一次求和
 * 2.前缀和
 * https://leetcode.com/problems/path-sum-iii/discuss/91884/Simple-AC-Java-Solution-DFS
 */
public class E437_PathSumIII {
    public static class TreeNode {
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
        //初始化前缀和0的次数为1，表示了从根节点出发的前缀和为target
        //    3         8
        //   / \       / \
        //  5   5   null  null
        preSum.put(0, 1);
        return helper(root, 0, sum);
    }

    public int helper(TreeNode root, int curSum, int target) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        //看前缀和表里是否存在 curSum - target, 存在则表示树中存在子数组和为target
        //逆向思维，求arr[i...j]为target不好求，则可以先求arr[0...i], arr[0...j]，则arr[i...j] = arr[0...j] - a[0...i]
        //下面两行不能交换，防止元素自身val值=curSum - target, 如输入[1] 0
        int count = preSum.getOrDefault(curSum - target, 0);
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        //次数都累加到父节点的次数上
        count += helper(root.left, curSum, target) + helper(root.right, curSum, target);
        //将到达当前节点的前缀和减1，相当于回溯
        preSum.put(curSum, preSum.get(curSum) - 1);
        //返回次数并累加到父节点，最终返回并累加到树的根节点
        return count;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(-2);

        TreeNode node3 = new TreeNode(3);
        node3.left = node1;
        node3.right = node2;

        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node5.right = node4;

        TreeNode node6 = new TreeNode(5);
        node6.left = node3;
        node6.right = node5;

        TreeNode node7 = new TreeNode(11);
        TreeNode node8 = new TreeNode(-3);
        node8.right = node7;

        TreeNode node9 = new TreeNode(10);
        node9.left = node6;
        node9.right = node8;

        System.out.println(new E437_PathSumIII().pathSum2(node9, 8));
    }
}
