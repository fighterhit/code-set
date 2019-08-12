package NewCoderProblemSet.Other.PDD.P2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/discuss/221065?type=0&order=0&pos=6&page=2
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] ss = s.split(" ");
        Deque<Integer> left = new LinkedList<Integer>();
        Deque<Integer> right = new LinkedList<Integer>();
        int suml, sumr, mid, result, lSize, rSize;
        suml = sumr = 0;
        int n, r, p;
        p = Integer.valueOf(ss[0]);
        n = Integer.valueOf(ss[1]);
        int[] num = new int[2 * n + 10];
        s = scanner.nextLine();
        ss = s.split(" ");
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.valueOf(ss[i - 1]);
        }
        Arrays.sort(num, 1, n + 1);
        for (int i = n + 1; i <= 2 * n; i++) {
            num[i] = num[i - n] + p;
        }
        mid = (1 + n) / 2;
        for (int i = 1; i <= mid; i++) {
            left.add(num[i]);
            suml += num[i];
        }
        for (int i = mid + 1; i <= n; i++) {
            right.add(num[i]);
            sumr += num[i];
        }
        lSize = left.size();
        rSize = right.size();
        result = sumr - suml + (lSize - rSize) * num[mid] - lSize * (lSize - 1) / 2 - rSize * (rSize + 1) / 2;
        for (int l = 2; l <= n; l++) {
            r = l + n - 1;
            mid = (l + r) / 2;
            suml -= left.pollFirst();
            right.add(num[r]);
            sumr += num[r];
            int temp = right.pollFirst();
            left.add(temp);
            sumr -= temp;
            suml += temp;
            lSize = left.size();
            rSize = right.size();
            result = Math.min(result, sumr - suml + (lSize - rSize) * num[mid] - lSize * (lSize - 1) / 2 - rSize * (rSize + 1) / 2);
        }
        System.out.println(result);
    }
}
