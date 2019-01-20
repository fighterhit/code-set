package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * Input: [1,2,2]
 * Output:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * 参考 M78_Subsets，
 */
public class M90_SubsetsII {
    List<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        //注意有重复元素时先排序，这样后面可判重跳过相同元素
        Arrays.sort(nums);
        generateSubsets(nums, new LinkedList<>(), 0);
        return res;
    }

    private void generateSubsets(int[] nums, LinkedList<Integer> ls, int start) {
        res.add(new LinkedList<>(ls));
        for (int i = start; i < nums.length; i++) {
            //跳过重复元素
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            ls.add(nums[i]);
            generateSubsets(nums, ls, i + 1);
            ls.removeLast();
        }
    }
}
