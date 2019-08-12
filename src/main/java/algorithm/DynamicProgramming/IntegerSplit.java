package algorithm.DynamicProgramming;

/**
 * 整数划分
 * https://www.cnblogs.com/nokiaguy/archive/2008/05/11/1192308.html
 */
public class IntegerSplit {

    //获取整数划分数
    static int split(int n) {
        return split(n, n);
    }

    //待划分整数 n，最大加数
    private static int split(int n, int m) {
        if (n < 1 || m < 1) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        if (n < m) {
            return split(n, n);
        }
        if (n == m) {
            return 1 + split(n, m - 1);
        }
        // n > m
        return split(n - m, m) + split(n, m - 1);
    }

    public static void main(String[] args) {
        System.out.println(split(6));
    }
}
