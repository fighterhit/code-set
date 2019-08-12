package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class H128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                //左边长度
                int leftLen = map.getOrDefault(num - 1, 0);
                //右边长度
                int rightLen = map.getOrDefault(num + 1, 0);
                //加上当前元素后连起来的长度
                int tmpLen = leftLen + rightLen + 1;
                res = Math.max(res, tmpLen);
                map.put(num, tmpLen);
                //注意修正左边界和右边界的长度！！！若边界元素不存在，则相当于还是push(num, tmpLen)
                map.put(num - leftLen, tmpLen);
                map.put(num + rightLen, tmpLen);
            }
        }
        return res;
    }

    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 1);
        }
        for (int num : nums) {
            forward(map, num);
        }
        return maxCount(map);
    }

    private int maxCount(Map<Integer, Integer> map) {
        int res = 0;
        for (Integer val : map.values()) {
            res = Math.max(val, res);
        }
        return res;
    }

    private int forward(Map<Integer, Integer> map, int num) {
        if (!map.containsKey(num)) {
            return 0;
        }
        int cnt = map.get(num);
        //次数大于 1，计算过则不再计算
        if (cnt > 1) {
            return cnt;
        }
        //向后计算
        cnt = forward(map, num + 1) + 1;
        map.put(num, cnt);
        return cnt;
    }
}
