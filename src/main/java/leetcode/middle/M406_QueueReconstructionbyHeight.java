package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k 是排在这个人前面且身高大于或等于 h 的人数。
 * 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 这道题给了一个队列，队列中的每个元素是一个 pair，分别为身高和前面身高不低于当前身高的人的个数，让我们重新排列队列，使得每个 pair 的第二个参数都满足题意。
 * 首先来看一种超级简洁的方法，给队列先排个序，按照身高高的排前面，如果身高相同，则第二个数小的排前面。
 * 然后新建一个空的数组，遍历之前排好序的数组，然后根据每个元素的第二个数字，将其插入到 res 数组中对应的位置
 * https://www.cnblogs.com/grandyang/p/5928417.html
 */
public class M406_QueueReconstructionbyHeight {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[][]{};
        }
//        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return 1;
                } else if (o1[0] > o2[0]) {
                    return -1;
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        List<int[]> res = new ArrayList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        new M406_QueueReconstructionbyHeight().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
    }
}
