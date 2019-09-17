package NewCoderProblemSet.Other.Kuaishou.P4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 无序数组形成等差数列的最大长度（不考虑元素顺序）
 */
public class Main {
    /* public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int N = sc.nextInt();
         int[] arr = new int[N];
         Arrays.sort(arr);
         int[][] dp = new int[N][N];
         for (int i = 0; i < N; i++) {
             dp[i][N - 1] = 2;
         }
         int i, k, max = 1;
         for (int j = N - 2; j >= 0; j--) {
             i = j - 1;
             k = j + 1;
             while (i >= 0 && k < N) {
                 if (arr[i] + arr[k] == 2 * arr[j]) {
                     dp[i][j] = dp[j][k] + 1;
                     max = Math.max(max, dp[i][j]);
                     i--;
                     k++;
                 } else if (arr[i] + arr[k] > 2 * arr[j]) {
                     i--;
                 } else {
                     k++;
                 }
             }
         }
         System.out.println(max);
     }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        ArrayList<Integer> res = new ArrayList<>(), tmp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                tmp.clear();
                tmp.add(i);
                tmp.add(j);
                int num = 2;
                int diff = arr[j] - arr[i];
                int cur = j;
                int next = j + 1;
                while (next < N) {
                    if (arr[next] - arr[cur] == diff) {
                        tmp.add(next);
                        cur = next;
                        num++;
                    }
                    next++;
                }
                if (num > res.size()) {
                    res = (ArrayList<Integer>) tmp.clone();
                }
            }
        }
        System.out.println(res.size());
    }
}
