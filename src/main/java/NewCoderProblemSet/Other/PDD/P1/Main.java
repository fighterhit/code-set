package NewCoderProblemSet.Other.PDD.P1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求数组中所有3个数组合中方差最小的组合的方差，返回方差保留2位小数
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        double res = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 3; i++) {
            int a = arr[i], b = arr[i + 1], c = arr[i + 2];
            res = Math.min(res, getSq(a, b, c));
        }
        System.out.printf("%.2f",res);;
    }

    static double getSq(int a, int b, int c) {
        double avg = (a + b + c) / 3.0;
        double res = 0;
        res += (a - avg) * (a - avg);
        res += (b - avg) * (b - avg);
        res += (c - avg) * (c - avg);
        return res / 3;
    }
}
