package NewCoderProblemSet.Other.BIGO.P2;

/**
 * M 个苹果放入 N 个盘子，可允许有空，共有多少分法
 * N^M : 每个苹果都有 N 种选择
 */
public class Main {
    int getNum(int m, int n) {
        //m == 0 || n == 1 表示 0 个苹果或者只有 1 个盘子方法，此时都只有 1 种方法
        if (m == 0 || n == 1) {
            return 1;
        }
        //当盘子数大于苹果数时，和去掉多余盘子等价
        if (n > m) {
            return getNum(m, m);
        }
        //盘子数小于等于苹果数时，两种情况：
        //1. 至少一个盘子空着
        //2. 每个盘子都有 1 个苹果
        return getNum(m, n - 1) + getNum(m - n, n);
    }
}
