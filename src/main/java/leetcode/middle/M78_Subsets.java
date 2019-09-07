package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        //子集长度
        for (int i = 0; i <= nums.length; i++) {
            helper(res, tmp, nums, 0, i);
        }
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start, int subSetLen) {
        if (tmp.size() == subSetLen) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            helper(res, tmp, nums, i + 1, subSetLen);
            tmp.remove(tmp.size() - 1);
        }
    }

    //https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        //注意：求子集循环从 start 开始，每次递归 i + 1，排列每次从 0 开始
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        new M78_Subsets().subsets(new int[]{1, 2, 3});
    }
}
