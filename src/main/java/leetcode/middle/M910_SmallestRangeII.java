package leetcode.middle;

import java.util.Arrays;

/**
 * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
 * 在此过程之后，我们得到一些数组 B。
 * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
 * <p>
 * 示例 1：
 * 输入：A = [1], K = 0
 * 输出：0
 * 解释：B = [1]
 * <p>
 * 示例 2：
 * 输入：A = [0,10], K = 2
 * 输出：6
 * 解释：B = [2,8]
 * <p>
 * 示例 3：
 * 输入：A = [1,3,6], K = 3
 * 输出：3
 * 解释：B = [4,6,3]
 * <p>
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 * <p>
 * 由于每个数字都需要加K或者减K，为了使得新数组的最大值最小值的差值最小，应该尽量使原数组中的较小的数字加K，较大的数字减K。
 * 所以最好是先给原数组排个序，然后在数组的某个位置 i 为界限，将原数组分为两段，前面所有的数字都加K，后面所有的数字都减K。
 * 前半段 [0, i] 中的最大值是 A[i]+K，最小值是 A[0]+K，后半段 [i+1, n-1] 范围内的最大值是 A[n-1]-K，最小值是 A[i+1]-K，所以整个数组的最大值是 A[i]+K 和 A[n-1]-K 中的较大值，最小值是 A[0]+K 和 A[i+1]-K 中的较小值，二者做差就是可能的结果了，遍历所有的i，用每次计算出的差值来更新结果 res 即可
 * https://github.com/grandyang/leetcode/issues/910
 * 对比 E908_SmallestRangeI
 */
public class M910_SmallestRangeII {
    public int smallestRangeII(int[] A, int K) {
        //先排序
        Arrays.sort(A);
        int len = A.length;
        //left, right 不变
        int left = A[0] + K, right = A[len - 1] - K;
        int res = A[len - 1] - A[0];
        //遍历分界点：i 左侧 +K，右侧 -K；
        //最大值是 A[i]+K 和 A[n-1]-K 中的较大值; 最小值是 A[0]+K 和 A[i+1]-K 中的较小值
        for (int i = 0; i < len - 1; i++) {
            int min = Math.min(left, A[i + 1] - K);
            int max = Math.max(right, A[i] + K);
            res = Math.min(res, max - min);
        }
        return res;
    }
}
