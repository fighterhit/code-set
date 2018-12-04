package leetcode.middle;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * <p>
 * Example 3:
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * <p>
 * 参考 E219_ContainsDuplicateII
 */
public class M220_ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> window = new TreeSet<>();
        Integer floor, ceil;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            floor = window.floor(nums[i]);
            ceil = window.ceiling(nums[i]);
            if (floor != null && (long) nums[i] - (long) floor <= t || ceil != null && (long) ceil - (long) nums[i] <= t) {
                return true;
            }
            window.add(nums[i]);
        }
        return false;
    }
}
