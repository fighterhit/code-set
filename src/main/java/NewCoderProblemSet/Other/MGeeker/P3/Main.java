package NewCoderProblemSet.Other.MGeeker.P3;

import java.util.Scanner;

/**
 * 饼干
 * n 台烤箱，需要烤 l 个饼，第 i 台每分钟最多烤 vi 个，最多只能烤 mi 个
 * 只有 t 分钟， k 个插头即 k 个烤箱
 * <p>
 * 第一行 l n t k
 * 接下来每行 vi, mi
 * <p>
 * 10 3 3 2
 * 1 10
 * 6 6
 * 3 5
 * <p>
 * yes
 * 2
 * <p>
 * 100 3 7 2
 * 1 10
 * 6 6
 * 3 5
 * <p>
 * No
 * 13
 */
public class Main {
    static int l, n, t, k;
    static int[] v, m, time, uesed;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        n = sc.nextInt();
        t = sc.nextInt();
        k = sc.nextInt();

        v = new int[n];
        m = new int[n];
        //分配时间
        time = new int[n];
        //要么为 0 要么为 1
        uesed = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            m[i] = sc.nextInt();
        }

    }
}