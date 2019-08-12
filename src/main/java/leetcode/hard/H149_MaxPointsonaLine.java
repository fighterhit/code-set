package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 * Example 2:
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * 两点确定一条直线，而且可以写成 y = ax + b 的形式，所有共线的点都满足这个公式。
 * 所以这些给定点两两之间都可以算一个斜率，每个斜率代表一条直线，对每一条直线，带入所有的点看是否共线并计算个数，这是整体的思路
 * 用 哈希表 来记录 斜率和共线点个数 之间的映射
 * 1. 斜率不存在，斜率记为 INT_MAX
 * 2. 两点重合，斜率记为 INT_MIN
 * <p>
 * 通过斜率来判断共线需要用到除法，而用 double 表示的双精度小数在有的系统里不一定准确，为了更加精确无误的计算共线，我们应当避免除法，从而避免无线不循环小数的出现，
 * 把除数和被除数都保存下来，不做除法，但要让这两数分别除以它们的最大公约数，这样例如8和4，4和2，2和1，这三组商相同的数就都会存到一个映射里面，同样也能实现我们的目标，而求 GCD 的函数如果用递归来写那么一行就搞定了
 * https://www.cnblogs.com/grandyang/p/4579693.html
 */
public class H149_MaxPointsonaLine {

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
            //每轮初始化重合点数
            int dup = 1;
            for (int j = i + 1; j < points.length; j++) {
                //和当前点重合的点，最后要加上
                if (points[j][0] == points[i][0] && points[j][1] == points[i][1]) {
                    dup++;
                    continue;
                }
                //为保证计算精度，只算差值，map 保存除以最大公约数的除数和被除数
                Map<Integer, Integer> tmpMap = new HashMap<>();
                int dx = points[j][0] - points[i][0], dy = points[j][1] - points[i][1];
                int d = gcd(dy, dx);
                dy /= d;
                dx /= d;
                tmpMap.put(dy, dx);
                //注意 map 计算 hash 的方法，HashMap 的 hash 为每个元素 key 的 hashCode 之和
                map.put(tmpMap, map.getOrDefault(tmpMap, 0) + 1);

            }
            //先与dup比较，防止都是重合的点
            res = Math.max(res, dup);
            for (Map.Entry<Map<Integer, Integer>, Integer> entry : map.entrySet()) {
                res = Math.max(res, entry.getValue() + dup);
            }
        }
        return res;
    }

    private int gcd(int dx, int dy) {
        if (dy == 0) {
            return dx;
        }
        return gcd(dy, dx % dy);
    }
}


