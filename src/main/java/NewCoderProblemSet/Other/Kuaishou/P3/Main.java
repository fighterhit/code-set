package NewCoderProblemSet.Other.Kuaishou.P3;

import java.util.Scanner;

/**
 * 求数组两部分和的差值最小值
 * 参考 ClosestTwoSum，01背包
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int sum = getSum(arr);
        int[][] dp = new int[N + 1][sum / 2 + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                dp[i + 1][j] = dp[i][j];
                if (arr[i] <= j && dp[i][j - arr[i]] + arr[i] > dp[i][j]) {
                    dp[i + 1][j] = dp[i][j - arr[i]] + arr[i];
                }
            }
        }
        System.out.println(sum - 2 * dp[N][sum / 2]);
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
}
