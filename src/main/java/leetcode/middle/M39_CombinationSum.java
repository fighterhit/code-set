package leetcode.middle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * https://leetcode.com/problems/combination-sum-ii/discuss/16878/Combination-Sum-I-II-and-III-Java-solution-(see-the-similarities-yourself)
 * <p>
 * <p>
 * 参考组合问题：
 * M17_LetterCombinationsofaPhoneNumber、M77_Combinations
 * M40_CombinationSumII、M216_CombinationSumIII、M377_CombinationSumIV
 * <p>
 * 排列问题：
 * M31_NextPermutation
 * M46_Permutations、M47_PermutationsII、M60_PermutationSequence
 * <p>
 * 输入数组无重复元素，每个元素可被选中多次
 * 题目要求返回结果不重复，则先对输入数组 candidates 排序
 */
public class M39_CombinationSum {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        //因为输入数组元素不重复，可排序也可不排序
//        Arrays.sort(candidates);
        helper(new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void helper(List<Integer> tmp, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        //注意组合从 start 开始
        for (int i = start; i < candidates.length; i++) {
            //注意，这里只选候选值小于等于 指定值 的元素
            if (candidates[i] <= target) {
                tmp.add(candidates[i]);
                //注意，这里是 i 而不是 i + 1，因为题目说明每个元素可被选中多次
                helper(tmp, candidates, target - candidates[i], i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    //元素不重求和为定值的组合，结果也要不重，每个元素可多次使用
    //https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        //因为已排序，这里可剪枝
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        }
        //注意：求和为指定值的组合从 start 开始，每次递归仍为 i 因为元素可多次使用，排列每次从 0 开始
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }
}
