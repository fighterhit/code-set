package NewCoderProblemSet.Other.Yitu.P20190809;

import java.util.*;

public class Main {
    static int[][] g;
    static int res;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        g = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            if (c == 1) {
                g[a][b] = 1;
                g[b][a] = 1;
            }
            if (c == 0) {
                g[a][b] = 2;
            }
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n + 1; j++) {
                Arrays.fill(visited[i], false);
            }
            int p = sc.nextInt(), q = sc.nextInt();
            res = 0;
            dfs(p, q);
            if (res == 0) {
                dfs(q, p);
                if (res == 2) {
                    System.out.println(2);
                } else if (res == 1) {
                    System.out.println(0);
                } else {
                    System.out.println(3);
                }
            } else {
                if (res == 2) {
                    System.out.println(1);
                } else if (res == 1) {
                    System.out.println(0);
                }
            }
        }
    }

    static void dfs(int p, int q) {
        if (g[p][q] != 0) {
            res = g[p][q];
            return;
        }
        for (int i = 1; i < g.length; i++) {
            if (g[p][i] > 0 && !visited[p][i]) {
                visited[p][i] = true;
                dfs(i, q);
            }
        }
    }
}

/*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();

        Set<Integer>[] eqMap = new HashSet[10002];
        Set<Integer>[] leMap = new HashSet[10002];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            //小于
            if (c == 0) {
                if (leMap[a] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    leMap[a] = set;
                } else {
                    Set<Integer> set = leMap[a];
                    set.add(b);
                }
            }
            //等于
            if (c == 1) {
                if (eqMap[a] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    eqMap[a] = set;
                } else {
                    Set<Integer> set = eqMap[a];
                    set.add(b);
                }

                if (eqMap[b] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(a);
                    eqMap[b] = set;
                } else {
                    Set<Integer> set = eqMap[b];
                    set.add(a);
                }
            }
        }

        for (int i = 0; i < 10000; i++) {
            if (eqMap[i] == null) {
                continue;
            }
            int key = i;
            Set<Integer> set = eqMap[key];
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (eqMap[kk] != null) {
                    res.addAll(eqMap[kk]);
                }
            }
            eqMap[key] = res;
        }
        for (int i = 0; i < 10000; i++) {
            if (leMap[i] == null) {
                continue;
            }
            int key = i;
            Set<Integer> set = leMap[i];
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (leMap[kk] != null)
                    res.addAll(leMap[kk]);
                if (eqMap[kk] != null)
                    res.addAll(eqMap[kk]);
            }
            leMap[key] = res;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int p = sc.nextInt(), q = sc.nextInt();
            //相等
            if (eqMap[p] != null && eqMap[p].contains(q)) {
                res.add(0);
            } else if (eqMap[q] != null && eqMap[q].contains(p)) {
                res.add(0);
            } else if (leMap[p] != null && leMap[p].contains(q)) {
                res.add(1);
            } else if (leMap[q] != null && leMap[q].contains(p)) {
                res.add(2);
            } else {
                res.add(3);
            }
        }
        for (int r : res) {
            System.out.println(r);
        }
    }
*/


 /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();

        Set<Integer>[] eqMap = new HashSet[10000];
        Set<Integer>[] leMap = new HashSet[10000];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            //小于
            if (c == 0) {
                if (leMap[a] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    leMap[a] = set;
                } else {
                    Set<Integer> set = leMap[a];
                    set.add(b);
                }
            }
            //等于
            if (c == 1) {
                if (eqMap[a] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    eqMap[a] = set;
                } else {
                    Set<Integer> set = eqMap[a];
                    set.add(b);
                }

                if (eqMap[b] == null) {
                    Set<Integer> set = new HashSet<>();
                    set.add(a);
                    eqMap[b] = set;
                } else {
                    Set<Integer> set = eqMap[b];
                    set.add(a);
                }
            }
        }

        for (int i = 0; i < 10000; i++) {
            if (eqMap[i] == null) {
                continue;
            }
            int key = i;
            Set<Integer> set = eqMap[key];
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (eqMap[kk] != null) {
                    res.addAll(eqMap[kk]);
                }
            }
            eqMap[key] = res;
        }
        for (int i = 0; i < 10000; i++) {
            if (leMap[i] == null) {
                continue;
            }
            int key = i;
            Set<Integer> set = leMap[i];
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (leMap[kk] != null)
                    res.addAll(leMap[kk]);
                if (eqMap[kk] != null)
                    res.addAll(eqMap[kk]);
            }
            leMap[key] = res;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int p = sc.nextInt(), q = sc.nextInt();
            //相等
            if (eqMap[p] != null && eqMap[p].contains(q)) {
                res.add(0);
            } else if (eqMap[q] != null && eqMap[q].contains(p)) {
                res.add(0);
            } else if (leMap[p] != null && leMap[p].contains(q)) {
                res.add(1);
            } else if (leMap[q] != null && leMap[q].contains(p)) {
                res.add(2);
            } else {
                res.add(3);
            }
        }
        for (int r : res) {
            System.out.println(r);
        }
    }*/


  /*  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();

        Map<Integer, Set<Integer>> eqMap = new HashMap<>();
        Map<Integer, Set<Integer>> leMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            //小于
            if (c == 0) {
                if (!leMap.containsKey(a)) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    leMap.put(a, set);
                } else {
                    Set<Integer> set = leMap.get(a);
                    set.add(b);
                }
            }
            //等于
            if (c == 1) {
                if (!eqMap.containsKey(a)) {
                    Set<Integer> set = new HashSet<>();
                    set.add(b);
                    eqMap.put(a, set);
                } else {
                    Set<Integer> set = eqMap.get(a);
                    set.add(b);
                }

                if (!eqMap.containsKey(b)) {
                    Set<Integer> set = new HashSet<>();
                    set.add(a);
                    eqMap.put(b, set);
                } else {
                    Set<Integer> set = eqMap.get(b);
                    set.add(a);
                }
            }
        }
        for (Map.Entry<Integer, Set<Integer>> eqEntry : eqMap.entrySet()) {
            int key = eqEntry.getKey();
            Set<Integer> set = eqEntry.getValue();
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (eqMap.containsKey(kk)) {
                    res.addAll(eqMap.get(kk));
                }
            }
            eqMap.put(key, res);
        }
        for (Map.Entry<Integer, Set<Integer>> leEntry : leMap.entrySet()) {
            int key = leEntry.getKey();
            Set<Integer> set = leEntry.getValue();
            Set<Integer> res = new HashSet<>(set);
            for (int kk : set) {
                if (leMap.containsKey(kk))
                    res.addAll(leMap.get(kk));
                if (eqMap.containsKey(kk))
                    res.addAll(eqMap.get(kk));
            }
            leMap.put(key, res);
        }

        for (int i = 0; i < k; i++) {
            int p = sc.nextInt(), q = sc.nextInt();
            //相等
            if (eqMap.containsKey(p) && eqMap.get(p).contains(q)) {
                System.out.println(0);
            } else if (eqMap.containsKey(q) && eqMap.get(q).contains(p)) {
                System.out.println(0);
            } else if (leMap.containsKey(p) && leMap.get(p).contains(q)) {
                System.out.println(1);
            } else if (leMap.containsKey(q) && leMap.get(q).contains(p)) {
                System.out.println(2);
            } else {
                System.out.println(3);
            }
        }
    }*/
