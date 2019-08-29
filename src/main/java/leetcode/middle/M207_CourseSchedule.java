package leetcode.middle;

import java.util.*;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * <p>
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * <p>
 * 提示:
 * 这个问题相当于查找一个环是否存在于有向图中。如果存在环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 * <p>
 * 判断有向图是否有环，按拓扑排序构建方法判断：
 * 先根据输入来建立这个有向图，并将入度数组也初始化好。
 * 然后我们定义一个queue变量，将所有入度为0的点放入队列中。
 * 然后开始遍历队列，从graph里遍历其连接的点，每到达一个新节点，将其入度减一，如果此时该点入度为0，则放入队列末尾。直到遍历完队列中所有的值，若此时还有节点的入度不为0，则说明环存在，返回false，反之则返回true。
 * DFS / BFS
 * https://www.cnblogs.com/grandyang/p/4484571.html
 * 拓扑排序
 * http://wangkuiwu.github.io/2013/04/11/topsort-cplus/
 *
 * 参考 M210_CourseScheduleII
 */
public class M207_CourseSchedule {
    /**
     * 拓扑排序问题，检查最后是否仍然存在入度不为0的节点，存在则 false，否则为 true
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            for (int no : adjs[node]) {
                in[no]--;
                if (in[no] == 0) {
                    queue.add(no);
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (in[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
