package NewCoderProblemSet.Other.Shopee.P2;

import java.util.Scanner;

/**
 * 66.67%
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(reverse(n));
    }

    static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                res = 0;
                break;
            } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && x < Integer.MIN_VALUE % 10)) {
                res = 0;
                break;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
