package NewCoderProblemSet.Other.FreeWheel;

/**
 * （笔试题）将数组分成两组，使两组的和的差的绝对值最小。类似 0-1 背包问题。
 * 1. dp[i][j]表示从 i 件物品中选出重量为 j 的物品的最大价值, weight[i], value[i] 分别代表第i件物品的重量和价值（在题目中，weight、value属于同一数组）。
 * 2. dp[i][j]表示 array 中 i 个元素的和 <=j，且是最接近j的元素集合。
 * dp[i-1][j-array[i]] 表示 array 中 i-1 个元素的和最接近 j-array[i]，所以 dp[i][j] 应该是 dp[i-1][j-array[i]]+array[i] 和 dp[i-1][j] 中最大的那个。
 * https://www.cnblogs.com/AndyJee/p/4543424.html
 * https://blog.csdn.net/BrilliantEagle/article/details/39860145
 */
public class ClosestTwoSum {
    static int getSum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }

    /**
     * 不返回路径，只返回最后差值
     */
    static void solution1() {
        int w[] = {2, 4, 5, 6, 7};
        int v[] = {2, 4, 5, 6, 7};
//        int[] w = {1, 0, 1, 7, 2, 4};
//        int[] v = {1, 0, 1, 7, 2, 4};
        int sum = getSum(w), len = w.length, C = sum / 2;
        int[][] dp = new int[len][C + 1];
        for (int i = 0; i <= sum / 2; i++) {
            if (i >= w[0]) {
                dp[0][i] = v[0];
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        System.out.println(Math.abs(sum - 2 * dp[len - 1][sum / 2]));
    }

    /**
     * 返回数组中选择的数字和最小差值
     */
    static void solution2() {
//        int w[] = {2, 4, 5, 6, 7};
//        int v[] = {2, 4, 5, 6, 7};
        int[] w = {1, 0, 1, 7, 2, 4};
        int[] v = {1, 0, 1, 7, 2, 4};
        int sum = getSum(w), len = w.length, C = sum / 2;
        boolean[] selected = new boolean[len];
        int[][] dp = new int[len][C + 1];
        for (int i = 0; i <= sum / 2; i++) {
            if (i >= w[0]) {
                dp[0][i] = v[0];
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {
//                    dp[i][j] = dp[i - 1][j - w[i]] + v[i];
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        //回溯，得到选择的数字
        for (int i = len - 1, j = C; i > 0; i--) {
            if (dp[i][j] > dp[i - 1][j]) {
                selected[i] = true;
                j = j - w[i];
            }
        }
        for (int i = 0; i < len; i++) {
            if (selected[i]) {
                System.out.println(i + 1);
            }
        }
//        System.out.println(Math.abs(sum - 2 * dp[len - 1][sum / 2]));
        System.out.println(dp[len - 1][C]);
    }

    public static void main(String[] args) {
//        solution1();
        solution2();
    }
}

/**
 * 只返回最小差值，不返回得到最小差值选择到的元素
 */
class DivideArrayTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array = {1, 0, 1, 7, 2, 4};
        System.out.println(getMaxDiff(array));

    }

    /* f[i][j]表示i个元素装容量为j的背包能装的最大容量 */
    public static int getMaxDiff(int[] array) {
        int sum = getSum(array);
        int length = array.length;
        int[][] f = new int[length + 1][sum / 2 + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                f[i + 1][j] = f[i][j];
                if (array[i] <= j && f[i][j - array[i]] + array[i] > f[i][j]) {
                    f[i + 1][j] = f[i][j - array[i]] + array[i];
                }
            }
        }
        return sum - 2 * f[length][sum / 2];
    }

    public static int getSum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

}
