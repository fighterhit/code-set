package leetcode.middle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * Output:
 * 2
 * 题目描述：气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都会刺破。求解最小的投飞镖次数使所有气球都被刺破。
 * 参考 M435_NonoverlappingIntervals，也是计算不重叠的区间个数，不过和 M435_NonoverlappingIntervals 的区别在于，[1, 2] 和 [2, 3] 在本题中算是重叠区间。
 */
public class M452_MinimumNumberofArrowstoBurstBalloons {
    public int findMinArrowShots(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        //lambda 表达式太慢
        //Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int cnt = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //当前区间起始位置 大于 end 算作一个不重叠区间
            if (intervals[i][0] > end) {
                cnt++;
                end = intervals[i][1];
            }
        }

        return cnt;
    }
}
