package NewCoderProblemSet.Other.MGeeker.P2;

import java.util.*;

/**
 * 乘积
 * n*m 矩阵找两个既不在同一行也不在同一列的数字，使乘积最大
 * 2 2
 * 1 2
 * 3 4
 * <p>
 * 6
 * <p>
 * <p>
 * 3 3
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 48
 */
public class Main {
    static long[][] board;
    static int n, m;
    static long res = Long.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        board = new long[n][m];
        List<List<long[]>> rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<long[]> ls = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
                ls.add(new long[]{board[i][j], j});
            }
            ls.sort(new Comparator<long[]>() {
                @Override
                public int compare(long[] o1, long[] o2) {
                    return (int) (o2[0] - o1[0]);
                }
            });
            rows.add(ls);
        }

        for (int i = 0; i < n - 1; i++) {
            long[] num1 = rows.get(i).get(0), num2 = rows.get(i).get(1);
            for (int j = i + 1; j < n; j++) {
                long[] num3 = rows.get(j).get(0), num4 = rows.get(j).get(1);
                if (num1[1] != num3[1]) {
                    res = Math.max(res, num1[0] * num3[0]);
                } else {
                    res = Math.max(res, Math.max(num1[0] * num4[0], num2[0] * num3[0]));
                }
            }
        }
        System.out.println(res);
    }
}
