package leetcode.easy;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 * @author Fighter Created on 2018/10/4.
 */
public class E350_IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer> ls = new LinkedList<>();

        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                ls.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] res = new int[ls.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ls.get(i);
        }
        return res;
    }
}
