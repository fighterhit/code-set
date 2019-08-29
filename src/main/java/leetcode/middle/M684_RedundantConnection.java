package leetcode.middle;

import structure.UnionFind.UF;

/**
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * <p>
 * 示例 1：
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 * 1
 * / \
 * 2 - 3
 * <p>
 * 示例 2：
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * 注意:
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * 更新(2017-09-26):
 * 我们已经重新检查了问题描述及测试用例，明确图是无向图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
 */
public class M684_RedundantConnection {
    /**
     * 逆向思维：
     * 每加入一条边，就进行环检测，一旦发现了环，就返回当前边。
     * 并查集：
     * 开始表示每个结点都是一个单独的组，所谓 Union Find 就是要让结点之间建立关联
     * 比如若 root[1] = 2，就表示结点 1 和结点 2 是相连的，root[2] = 3 表示结点 2 和结点 3 是相连的，如果我们此时新加一条边 [1, 3] 的话，我们通过 root[1] 得到 2，再通过 root[2] 得到 3，说明结点 1 有另一条路径能到结点 3，这样就说明环是存在的；如果没有这条路径，那么我们要将结点 1 和结点 3 关联起来，让root[1] = 3 即可
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] root = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            int x = find(root, edge[0]), y = find(root, edge[1]);
            if (x == y) {
                return edge;
            }
            root[x] = y;
        }
        return new int[0];
    }

    private int find(int[] root, int i) {
        if (root[i] == i) {
            return i;
        }
        return find(root, root[i]);
    }

    public int[] findRedundantConnection2(int[][] edges) {
        int n = edges.length;
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            //若两个节点已连接，则当前边即为冗余边，返回当前边即可
            if (uf.isConnect(x, y)) {
                return edge;
            }
            uf.union(x, y);
        }
        return new int[0];
    }

    private class UF {
        int[] id;

        public UF(int n) {
            id = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                id[i] = i;
            }
        }

        //连接
        void union(int u, int v) {
            int uid = find(u);
            int vid = find(v);
            if (uid == vid) {
                return;
            }
            for (int i = 1; i < id.length; i++) {
                if (id[i] == uid) {
                    id[i] = vid;
                }
            }
        }

        //查询
        boolean isConnect(int u, int v) {
            return find(u) == find(v);
        }

        int find(int p) {
            return id[p];
        }
    }
}
