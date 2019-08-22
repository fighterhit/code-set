package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 * <p>
 * Example 1:
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * Note: The length of the input array will not exceed 20,000.
 * <p>
 * 最长和谐序列中最大数和最小数之差正好为 1(不是小于等于1)，应该注意的是序列的元素不一定是数组的连续元素。
 */
public class E594_LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num + 1) + map.get(num));
            }
        }
        return res;
    }
}
