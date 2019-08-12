package sword.ch6;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://www.kancloud.cn/digest/pieces-algorithm/163635
 */
public class P294_DicesProbability {

    public static List<Map.Entry<Integer, Double>> dicesSum2(int n) {
        int face = 6;
        int pointNum = face * n;
        //空间优化：只保存上下两行即可
        long[][] dp = new long[2][pointNum + 1];
        int flag = 0;
        for (int i = 1; i <= face; i++) {
            dp[flag][i] = 1;
        }
        //从第二个骰子开始
        for (int i = 2; i <= n; i++) {
            //初始化新行, k 表示前 i 个骰子可取的点数
            for (int k = 0; k <= pointNum; k++) {
                dp[1 - flag][k] = 0;
            }
            //前i个骰子总点数：最小从 i 开始
            for (int j = i; j <= pointNum; j++) {
                //当前骰子取的点数：①小于面数face ②小于要求的总点数 j
                for (int k = 1; k <= face && k <= j; k++) {
                    dp[1 - flag][j] += dp[flag][j - k];
                }
            }
            flag = 1 - flag;
        }
        double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> res = new ArrayList<>();
        Map.Entry<Integer, Double> tmp;
        for (int i = n; i <= pointNum; i++) {
            tmp = new AbstractMap.SimpleEntry<>(i, dp[flag][i] / totalNum);
            res.add(tmp);
        }
        return res;
    }

    public static void dicesSum(int n) {
        int face = 6;
        int pointNum = face * n;
        //前 i 个骰子点数和为 j
        int[][] dp = new int[n + 1][pointNum + 1];
        //初始化第 1 个骰子出现的点数次数
        for (int i = 1; i <= face; i++) {
            dp[1][i] = 1;
        }
        //从第 2 个骰子开始
        for (int i = 2; i <= n; i++) {
            // 使用 i 个骰子最小点数为 i
            for (int j = i; j <= pointNum; j++) {
                // 当前骰子可取的点数k，小于面数face 并且 也要小于等于总点数j
                for (int k = 1; k <= face && k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        //总次数是全排列
        double totalNum = Math.pow(face, n);
        for (int i = n; i <= pointNum; i++) {
            System.out.printf("n: %d, sum: %d : %f \n", n, i, dp[n][i] / totalNum);
        }
    }

    static int CountNumber(int n, int s) {
        if (s < n || s > 6 * n) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return CountNumber(n - 1, s - 1) + CountNumber(n - 1, s - 2) + CountNumber(n - 1, s - 3)
                + CountNumber(n - 1, s - 4) + CountNumber(n - 1, s - 5) + CountNumber(n - 1, s - 6);
    }

    static void listDiceProbability(int n) {
        int i = 0;
        int nTotal = (int) Math.pow(6, n);
        for (i = n; i <= 6 * n; i++) {
            System.out.printf("P(s=%d) = %d/%d\n", i, CountNumber(n, i), nTotal);
        }
    }

    public static void main(String[] args) {
//        listDiceProbability(6);
//        dicesSum(3);
        System.out.println(dicesSum2(2));
    }
}
