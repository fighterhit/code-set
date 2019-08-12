package NewCoderProblemSet.Other.MGeeker.P1;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 平均数
 * 给 n 个数字，每次可以选两个，删这两个数字并加入它们平均数，最大化剩下的数字。
 * 4
 * 3 7 2 1
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.printf("%.4f", 0.0);
        }
        if (n == 1) {
            System.out.printf("%.4f", sc.nextDouble());
        }
        PriorityQueue<Double> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextDouble());
        }
        while (queue.size() > 1) {
            double a = queue.poll();
            double b = queue.poll();
            queue.add((a + b) / 2);
        }
        System.out.printf("%.4f", queue.poll());
    }
}