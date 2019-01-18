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
 */
public class M46_Permutations {
    List<List<Integer>> res;
    // 与 M17_LetterCombinationsofaPhoneNumber 区别
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        used = new boolean[nums.length];
        if (nums == null || nums.length == 0) {
            return res;
        }
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
}
