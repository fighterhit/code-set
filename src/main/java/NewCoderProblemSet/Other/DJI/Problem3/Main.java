package NewCoderProblemSet.Other.DJI.Problem3;

import java.util.*;

/**
 * Wrong Answer 不过
 */
public class Main {
    static long ans = 0;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //开销
            int V = sc.nextInt();
            //零食种类
            int N = sc.nextInt();
            int[] price = new int[N];
            for (int i = 0; i < N; i++) {
                price[i] = sc.nextInt();
            }
            int likeM = sc.nextInt();
            //喜欢的物品索引
            int[] like = new int[likeM];
            for (int i = 0; i < likeM; i++) {
                like[i] = sc.nextInt() - 1;
            }
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < likeM; i++) {
                ls.add(price[like[i]]);
            }
            ans = 0;
            helper(V, price, likeM, like, 0, ls);
            System.out.println(ans % 10000007);
            set.clear();
        }
    }

    static StringBuilder sb = new StringBuilder();

    private static void helper(int v, int[] price, int likeM, int[] like, int start, List<Integer> ls) {
        if (v == 0) {
            ans++;
            System.out.println(sb.toString());
            return;
        }
        for (int i = start; i < likeM; i++) {
            if (v < ls.get(i)) {
                continue;
            }
            v -= ls.get(i);
            sb.append(like[i]);
            helper(v, price, likeM, like, start, ls);
            sb.deleteCharAt(sb.length() - 1);
            v += ls.get(i);
        }
    }
}
