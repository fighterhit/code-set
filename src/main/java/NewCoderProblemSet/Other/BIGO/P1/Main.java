package NewCoderProblemSet.Other.BIGO.P1;

import java.util.Scanner;

/**
 * 一个小球从高为 H 的地方下落，下落弹起高度为下落的1/2，计算从H高度下落到第n次弹起往返总路程
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double h = sc.nextInt();
        int n = sc.nextInt();
        if (h == 0 || n == 0) {
            return;
        }
        //第1次往返是 h+h/2，第2次是前一次一半...形成等比数列，用等比数列公式求和即可
        System.out.println((h + h / 2) * (2 - 1 / Math.pow(2, n - 1)));
    }
}
