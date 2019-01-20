package leetcode.middle;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 参考 M90_SubsetsII
 */
public class M78_Subsets {
    List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        generateSubsets(nums, new LinkedList<>(), 0);
        return res;
    }

    private void generateSubsets(int[] nums, LinkedList<Integer> ls, int start) {
        System.out.print("ls: " + ls);
        res.add(new LinkedList<>(ls));
        System.out.println("     res: " + res);
        for (int i = start; i < nums.length; i++) {
            ls.add(nums[i]);
            generateSubsets(nums, ls, i + 1);
            ls.removeLast();
        }
    }

    public static void main(String[] args) {
        new M78_Subsets().subsets(new int[]{1, 2, 3});
    }
}
