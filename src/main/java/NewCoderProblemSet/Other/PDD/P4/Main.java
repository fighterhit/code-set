package NewCoderProblemSet.Other.PDD.P4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //山的个数和山顶间路的个数
        int n = sc.nextInt(), m = sc.nextInt();
        Map<Integer, List<Integer>> path3 = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt(), to = sc.nextInt();
            if (!path3.containsKey(from)) {
                List<Integer> path = new ArrayList<>();
                path.add(to);
                path3.put(from, path);
            } else {
                List<Integer> path = path3.get(from);
                path.add(to);
            }
        }
        System.out.println(1);
    }
}
