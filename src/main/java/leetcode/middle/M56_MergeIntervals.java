package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * <p>
 * 参考 H57_InsertInterval 插入新区间
 */
public class M56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        //[start, end] 按数组第 1 个元素 start 由小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            //当前区间起始位置比上一区间结束位置小，则判断上一区间结束位置是否小于当前区间结束位置，小于则更新上一区间结束位置即合并
            if (intervals[i][0] <= pre[1]) {
                if (pre[1] < intervals[i][1]) {
                    pre[1] = intervals[i][1];
                }
            } else {
                res.add(pre);
                pre = intervals[i];
            }
        }
        //注意添加最后一个区间
        res.add(pre);
        return res.toArray(new int[1][1]);
    }

    public static void main(String[] args) {
        new M56_MergeIntervals().merge(new int[][]{
                {2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}
        });
    }
}
