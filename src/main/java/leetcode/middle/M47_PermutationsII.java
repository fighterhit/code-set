package leetcode.middle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class M47_PermutationsII {
    List<List<Integer>> res;
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        generatePermutations(nums, new LinkedList<>());
        return res;
    }

    private void generatePermutations(int[] nums, LinkedList<Integer> ls) {
        if (ls.size() == nums.length) {
            res.add(new LinkedList<>(ls));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            ls.add(nums[i]);
            generatePermutations(nums, ls);
            used[i] = false;
            ls.removeLast();
        }
    }
}
