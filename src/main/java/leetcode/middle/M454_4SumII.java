package leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 已知数据规模（本题N=500）可以推测使用哪种级别复杂度的算法！！！本题时间、空间复杂度均为O(N*N)
 */
public class M454_4SumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //保存相同和的次数
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int key = 0 - A[i] - B[j];
                if (map.containsKey(key)) {
                    res += map.get(key);
                }
            }
        }
        return res;
    }
}
