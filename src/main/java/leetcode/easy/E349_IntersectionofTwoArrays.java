package leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 *
 *
 * @author Fighter Created on 2018/10/4.
 */
public class E349_IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i : nums1) {
            treeSet.add(i);
        }
        List<Integer> ls = new ArrayList<>();
        for (int i : nums2) {
            if (treeSet.contains(i)){
                ls.add(i);
                treeSet.remove(i);
            }
        }
        int[] res = new int[ls.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ls.get(i);
        }
        return res;
    }
}
