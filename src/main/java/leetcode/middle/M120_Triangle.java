package leetcode.middle;

import java.util.*;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * <p>
 * 每个结点能往下走的只有跟它相邻的两个数字，那么每个位置 (i, j) 也就只能从上层跟它相邻的两个位置过来，也就是 (i-1, j-1) 和 (i-1, j) 这两个位置，那么状态转移方程：
 * triangle[i][j] += min(triangle[i - 1][j - 1], triangle[i - 1][j])
 * <p>
 * https://algorithm.yuanbin.me/zh-hans/dynamic_programming/triangle.html
 */
public class M120_Triangle {
    //直接用原 triangle 列表
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                //边界值，0 处只能从上向下来，因此直接加
                if (j == 0) {
                    //上层当前列的值
                    int a = triangle.get(i - 1).get(j);
                    //当前层当前列的旧值
                    int b = triangle.get(i).get(j);
                    //赋新值
                    triangle.get(i).set(j, a + b);
                }
                //边界值，每一层最后位置处
                else if (j == triangle.get(i).size() - 1) {
                    //上一层前一列的值
                    int a = triangle.get(i - 1).get(j - 1);
                    //当前层当前列旧值
                    int b = triangle.get(i).get(j);
                    triangle.get(i).set(j, a + b);
                } else {
                    //上一层前一列和当前列的值
                    int a1 = triangle.get(i - 1).get(j - 1), a2 = triangle.get(i - 1).get(j);
                    //当前层当前列旧值
                    int b = triangle.get(i).get(j);
                    //取上一层前一列和当前列的值的最小值，加上当前层当前列旧值
                    triangle.get(i).set(j, Math.min(a1, a2) + b);
                }
            }
        }
        //找最后一行最小值
        List<Integer> lastLine = triangle.get(n - 1);
        int min = lastLine.get(0);
        for (int i = 1; i < triangle.get(n - 1).size(); i++) {
            if (lastLine.get(i) < min) {
                min = lastLine.get(i);
            }
        }
        return min;
    }

    List<List<Integer>> t;
    int sum = Integer.MAX_VALUE;

    //深度优先遍历，得到每条路径和，求其最小值
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        t = triangle;
        dfs(0, 0, 0);
        return sum;
    }

    private void dfs(int x, int y, int tmpSum) {
        if (x == t.size()) {
            sum = Math.min(sum, tmpSum);
            return;
        }
        dfs(x + 1, y, tmpSum + t.get(x).get(y));
        dfs(x + 1, y + 1, tmpSum + t.get(x).get(y));
    }

    //分治法: 不带 HashMap，Time Limit Exceeded
    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        t = triangle;
        return DC(0, 0);
    }

    private int DC(int x, int y) {
        if (x == t.size()) {
            return 0;
        }
        return Math.min(DC(x + 1, y + 1), DC(x + 1, y)) + t.get(x).get(y);
    }


    //分治法: 带 HashMap，Time Limit Exceeded
    //保存中间计算结果
    int[][] map;

    public int minimumTotal4(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        map = new int[triangle.size()][];
        for (int i = 0; i < triangle.size(); i++) {
            map[i] = new int[triangle.get(i).size()];
            for (int j = 0; j < triangle.get(i).size(); j++) {
                map[i][j] = Integer.MIN_VALUE;
            }
        }
        t = triangle;
        return DC2(0, 0);
    }

    private int DC2(int x, int y) {
        if (x == t.size()) {
            return 0;
        }
        if (map[x][y] > 0) {
            return map[x][y];
        }
        int x1y = DC2(x + 1, y);
        int x1y1 = DC2(x + 1, y + 1);
        map[x][y] = Math.min(x1y, x1y1) + t.get(x).get(y);
        return map[x][y];
    }


    //自底向上DP：从 (x,y) 出发走到最后一行的最短路径和
    public int minimumTotal5(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        //总行数
        int n = triangle.size();
        map = new int[n][];
        for (int i = 0; i < triangle.size(); i++) {
            map[i] = new int[triangle.get(i).size()];
        }
        //因为是自底向上，所以先初始化最底行
        for (int i = 0; i < n; i++) {
            map[n - 1][i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                map[i][j] = Math.min(map[i + 1][j], map[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return map[0][0];
    }

    //自上到下DP：从 (0,0) 走到 (x,y)的最短路径和 同法一，注意左右边界

    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList() {
            {
                add(2);
            }
        };
        res.add(ls);
        ls = new ArrayList() {
            {
                add(3);
                add(4);

            }
        };
        res.add(ls);
        ls = new ArrayList() {
            {
                add(6);
                add(5);
                add(7);
            }
        };
        res.add(ls);
        ls = new ArrayList() {
            {
                add(4);
                add(1);
                add(8);
                add(3);
            }
        };
        res.add(ls);
        System.out.println(res);
        System.out.println(new M120_Triangle().minimumTotal2(res));
        ;
    }
}
