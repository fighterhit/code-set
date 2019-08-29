package leetcode.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * <p>
 * 示例 2:
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 * <p>
 * 参考 M207_CourseSchedule，上题只判断是否存在排课顺序，该题返回排课结果
 */
public class M210_CourseScheduleII {
    /**
     * 该题是 Course Schedule 的扩展，那道题只让判断是否能完成所有课程，即检测有向图中是否有环，而这道题我们得找出要上的课程的顺序，即有向图的拓扑排序 Topological Sort
     * 由于有之前那道的基础，而此题正是基于之前解法的基础上稍加修改，我们从 queue 中每取出一个数组就将其存在结果中，最终若有向图中有环，则结果中元素的个数不等于总课程数，将结果清空即可
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        //邻居链表
        ArrayList<Integer>[] adjs = new ArrayList[numCourses];
        //每个顶点入度数
        int[] in = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjs[i] = new ArrayList<>();
        }
        //注意不能用下面该句初始化，否则 adjs 都指向同一个 ArrayList
        //Arrays.fill(adjs, new ArrayList<>());

        for (int[] edge : prerequisites) {
            //edge[0]: 子节点 edge[1]: 父节点
            //edge[1] 指向 edge[0]
            adjs[edge[1]].add(edge[0]);
            in[edge[0]]++;
        }
        //将所有入度为 0 放队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (queue.size() > 0) {
            int node = queue.poll();
            res.add(node);
            for (int no : adjs[node]) {
                in[no]--;
                if (in[no] == 0) {
                    queue.add(no);
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (in[i] != 0) {
                return new int[0];
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
