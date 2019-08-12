package NewCoderProblemSet.Other.MGeeker.P4;

import java.util.Scanner;

/**
 * 子段和
 * 一个整数序列的最大连续子段和指的是从序列中选出连续的一段数字，使之和最大
 * 现给定一个整数序列，可以删连续一段（可以不删），最大化删除后的最大连续子段和
 * 第一行表示数组长度
 * <p>
 * 5
 * 1 3 -2 4 5
 * <p>
 * 6
 * 1 2 3 4 5 6
 */
public class Main {
    static int n;
    static long[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long max = Long.MIN_VALUE, sum = 0;
        long[] preSum = new long[n];
        int begin = Integer.MAX_VALUE, end = Integer.MAX_VALUE;
        preSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < 0) {
                if (begin == Integer.MAX_VALUE) {
                    begin = i;
                }
            }
            preSum[i] = preSum[i - 1] + arr[i];

        }

        System.out.println(max);
    }
}