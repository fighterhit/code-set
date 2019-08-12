package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 * Combination Sum I, II and III Java solution (see the similarities yourself)
 * https://leetcode.com/problems/combination-sum-ii/discuss/16878/Combination-Sum-I-II-and-III-Java-solution-(see-the-similarities-yourself)
 * <p>
 * 输入数组可能有重复元素，每个元素只能被选中一次
 * 题目要求返回结果不重复，则先对输入数组 candidates 排序
 */
public class M40_CombinationSumII {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        //先排序
        Arrays.sort(candidates);
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
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //注意，这里只选候选值小于等于 指定值 的元素
            if (candidates[i] <= target) {
                tmp.add(candidates[i]);
                //注意，这里是 i + 1 而不是 i，因为题目说明每个元素只能被选中一次
                helper(tmp, candidates, target - candidates[i], i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        new M40_CombinationSumII().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }
}
