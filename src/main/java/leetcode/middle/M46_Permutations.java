package leetcode.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 与 M17_LetterCombinationsofaPhoneNumber 区别
 * 回溯法通用方法(Subsets, Permutations, Combination Sum, Palindrome Partioning)：
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * <p>
 * 排列问题每个元素只能选中一次，和组合问题区别
 * 输入数组无重复元素，故无需排序，每个元素只能被选中1次，且需 for 循环遍历每个元素（每次从 0 开始，和组合问题区别），故需要 visied 数组记录遍历过的元素
 * <p>
 * 参考元素重复 M47_PermutationsII
 */
public class M46_Permutations {
    List<List<Integer>> res;
    // 与 M17_LetterCombinationsofaPhoneNumber 区别
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        used = new boolean[nums.length];
        generatePermutation(nums, 0, new LinkedList<>());
        return res;
    }

    //ls中保存了1个有index个元素的排列，向列表末尾添加第index+1个元素形成新的排列
    private void generatePermutation(int[] nums, int index, LinkedList<Integer> ls) {
        if (index == nums.length) {
            res.add((List<Integer>) ls.clone());
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                ls.add(nums[i]);
                generatePermutation(nums, index + 1, ls);
                //回溯返回后要清理
                used[i] = false;
                ls.removeLast();
            }
        }
    }

    //ls中保存了1个有index个元素的排列，向列表末尾添加第index+1个元素形成新的排列
    private void generatePermutation2(int[] nums, int index, LinkedList<Integer> ls) {
        if (index == nums.length) {
            res.add((List<Integer>) ls.clone());
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!ls.contains(nums[i])) {
                ls.add(nums[i]);
                generatePermutation2(nums, index + 1, ls);
                //回溯返回后要清理
                ls.removeLast();
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] v = new boolean[nums.length];
        helper(res, tmp, nums, v);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] v) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (v[i]) {
                continue;
            }
            v[i] = true;
            tmp.add(nums[i]);
            helper(res, tmp, nums, v);
            v[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

    //求元素不重的数组的全排列，不需 visited 标记数组，此时可直接用 contains() 方法
    //https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        //注意：求全排列每次从 0 开始，求子集每次从 start 开始
        for (int i = 0; i < nums.length; i++) {
            //注意：因为元素不重，这里可直接用 contains() 判断是否存在，不需用 visited 数组
            if (tempList.contains(nums[i])) {
                continue; // element already exists, skip
            }
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }
}
