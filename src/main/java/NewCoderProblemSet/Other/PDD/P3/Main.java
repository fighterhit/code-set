package NewCoderProblemSet.Other.PDD.P3;

import java.util.Scanner;

/**
 *
 */
public class Main {
    static long cnt = 0;
    static int n, sum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //长度，target
        n = sc.nextInt();
        sum = sc.nextInt();
        helper(n, sum,1);

    }

    private static void helper(int n, int sum, int start) {
        if (n == 0 && sum == 0){
            cnt++;
            return;
        }
        if (n < 0){
            return;
        }
        if (sum < start){
            return;
        }
        for (int i = start; i <= sum ; i++) {
            helper(n - 1,sum - i, i + 1);
        }
    }
    /*

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //长度，target
        n = sc.nextInt();
        sum = sc.nextInt();
        helper(0, 0, new ArrayList<>());
        System.out.println(cnt % 1000000007);
    }

    static void helper(int start, int tmpSum, List<Integer> ls) {
        if (ls.size() == n) {
            if (tmpSum == sum) {
                cnt++;
                return;
            }
            return;
        }
        for (int i = start + 1; i <= sum - 1; i++) {
            if (tmpSum > sum){
                break;
            }
            ls.add(i);
            helper(i, tmpSum + i, ls);
            ls.remove(ls.size() - 1);
        }
    }*/
}
