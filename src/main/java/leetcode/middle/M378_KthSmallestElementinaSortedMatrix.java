package leetcode.middle;

import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * 示例:
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 * <p>
 * https://www.cnblogs.com/grandyang/p/5727892.html
 */
public class M378_KthSmallestElementinaSortedMatrix {
    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

    /**
     * 思路类似合并 k 个有序链表，先把每行首元素放入堆中，每次从堆中取一个并将同行后面元素加入堆中
     */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            queue.add(new Tuple(i, 0, matrix[i][0]));
        }
        int i = 0;
        while (i < k - 1) {
            Tuple t = queue.poll();
            i++;
            if (t.y + 1 == n)
                continue;
            queue.add(new Tuple(t.x, t.y + 1, matrix[t.x][t.y + 1]));
        }
        return queue.poll().val;
    }

    /**
     * 思路同上，只是将每列看作 k 个有序链表
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(new Tuple(0, i, matrix[0][i]));
        }
        //取出 k-1 个
        for (int i = 0; i < k - 1; i++) {
            //每取出一个就放如下行同列元素
            Tuple t = queue.poll();
            if (t.x == m - 1) {
                continue;
            }
            queue.add(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return queue.poll().val;
    }

    /**
     * 本解法的整体时间复杂度为 O(nlgn*lgX)，其中X为最大值和最小值的差值，参见代码如下：
     */
    public int kthSmallest3(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int l = matrix[0][0], r = matrix[m - 1][n - 1];
        //注意等号
        while (l < r) {
            int mid = l + (r - l) / 2, cnt = 0;
            for (int i = 0; i < m; i++) {
                //注意等号
                for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

}
