package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class E697_DegreeofanArray {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int degree = 0, res = 0;
        HashMap<Integer, Integer> counter = new HashMap<>(), firstIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //putIfAbsent 只会加入一次，返回的是上次的 value 或 null
            firstIndex.putIfAbsent(nums[i], i);
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
            if (counter.get(nums[i]) > degree) {
                degree = counter.get(nums[i]);
                res = i - firstIndex.get(nums[i]) + 1;
            } else if (counter.get(nums[i]) == degree) {
                res = Math.min(res, i - firstIndex.get(nums[i]) + 1);
            }
        }
        return res;
    }

    public int findShortestSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0, n = nums.length, min = n;
        HashMap<Integer, Integer> counter = new HashMap<>();
        Map<Integer, int[]> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //第一次出现设置首位置
            if (counter.getOrDefault(nums[i], 0) == 0) {
                counter.put(nums[i], 1);
                pos.put(nums[i], new int[]{i, i});
            }
            //之后出现设置尾位置
            else {
                counter.put(nums[i], counter.get(nums[i]) + 1);
                pos.get(nums[i])[1] = i;
            }
            res = Math.max(res, counter.get(nums[i]));
        }
        for (int num : nums) {
            if (counter.get(num) == res) {
                int[] p = pos.get(num);
                min = Math.min(min, p[1] - p[0] + 1);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        new E697_DegreeofanArray().findShortestSubArray2(new int[]{1});
    }
}
