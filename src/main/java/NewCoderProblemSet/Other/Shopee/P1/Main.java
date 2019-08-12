package NewCoderProblemSet.Other.Shopee.P1;

import java.util.Scanner;

/**
 * 过 67%
 */
public class Main {
    static int[] na;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //候选人数
        int n = sc.nextInt(), m = 0, index = -1;
        int[][] arr = new int[n][60];
        int resIndex = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            //m个指标
            m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                index = sc.nextInt();
                arr[i][index - 1] = 1;
            }
        }
        int naN = sc.nextInt();
        na = new int[naN];
        for (int i = 0; i < naN; i++) {
            int kk = sc.nextInt();
            na[i] = kk - 1;
        }
        for (int i = 0; i < n; i++) {
            int tmp = check(arr[i]);
            if (tmp > max) {
                max = tmp;
                resIndex = i + 1;
            }
        }
        System.out.println(resIndex);
    }

    static int check(int[] arr) {
        int sum = 0;
        for (int i : na) {
            sum += arr[i];
        }
        return sum;
    }

}
