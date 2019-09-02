package leetcode.middle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Input: [ [1,2], [1,2], [1,2] ]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * <p>
 * Input: [ [1,2], [2,3] ]
 * Output: 0
 * <p>
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * 题目描述：计算让一组区间不重叠所需要移除的区间个数。
 * 逆向思路：先计算最多能组成的不重叠区间个数，然后用 区间总个数 减去 不重叠区间的个数。
 * <p>
 * 在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
 * 按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
 */
public class M435_NonoverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        /**
         * 注意，先按结束位置排序：
         * 区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。每次选择结尾最小，并且和前一个区间不重叠的区间。
         */
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
            //注意：这里等于 end 也算作不重叠
            //当前区间起始位置 intervals[i][0] 大于 上个区间结束位置 end 算作一个不重叠区间，且要更新 end
            if (intervals[i][0] >= end) {
                cnt++;
                end = intervals[i][1];
            }
        }

        return intervals.length - cnt;
    }
}
