package leetcode.easy;

/**
 * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择任意 x 满足 -K <= x <= K，并将 x 加到 A[i] 中。
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
 * 输出：0
 * 解释：B = [3,3,3] 或 B = [4,4,4]
 * <p>
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 * <p>
 * 如何让二者之间的差值最小呢？
 * 当然是让最大值尽可能变小，最小值尽可能变大了，所以最大值 mx 要加上 -K，而最小值 mn 要加上K，然后再做减法，即 (mx-K)-(mn+K) = mx-mn+2K
 * https://github.com/grandyang/leetcode/issues/908
 * 对比 M910_SmallestRangeII
 */
public class E908_SmallestRangeI {
    public int smallestRangeI(int[] A, int K) {
        int max = A[0], min = A[0];
        for (int n : A) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        return Math.max(0, max - min - 2 * K);
    }
}
