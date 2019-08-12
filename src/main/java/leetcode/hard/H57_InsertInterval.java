package leetcode.hard;

import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * 参考 M56_MergeIntervals 合并重叠区间，本题默认已按起始位置好排序
 */
public class H57_InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return intervals;
        }
        int cur = 0, len = intervals.length;
        //先将结束位置 小于 新区间起始位置的区间加入结果列表中
        while (cur < len && intervals[cur][1] < newInterval[0]) {
            res.add(intervals[cur]);
            cur++;
        }
        //下面合并和区间有重合的区间，即当前遍历到的区间起始位置 小于等于 新区间结束区间位置，注意这里等于也需要合并
        while (cur < len && intervals[cur][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[cur][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[cur][1], newInterval[1]);
            cur++;
        }
        res.add(newInterval);
        while (cur < len) {
            res.add(intervals[cur++]);
        }
        return res.toArray(new int[0][0]);
    }


}
