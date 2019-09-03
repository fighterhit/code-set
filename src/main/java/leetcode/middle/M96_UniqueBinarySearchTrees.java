package leetcode.middle;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 这道题实际上是 Catalan Number 卡塔兰数的一个例子
 * 把n = 0 时赋为1，因为空树也算一种二叉搜索树，那么n = 1时的情况可以看做是其左子树个数乘以右子树的个数，左右字数都是空树，所以1乘1还是1。那么n = 2时，由于1和2都可以为树根，分别算出来，再把它们加起来即可。n = 2的情况可由下面式子算出：
 * dp[2] =  dp[0] * dp[1]　　　(1为根的情况)
 * 　　　　+ dp[1] * dp[0]　　  (2为根的情况)
 * <p>
 * 同理可写出 n = 3 的计算方法：
 * dp[3] =  dp[0] * dp[2]　　　(1为根的情况)
 * 　　　　+ dp[1] * dp[1]　　  (2为根的情况)
 * 　　　  + dp[2] * dp[0]　　  (3为根的情况)
 * C(0) = 1;
 * C(n + 1) = sum( C(i) * C(n - i))   [0...i...n] , n >= 0
 * http://www.cnblogs.com/grandyang/p/4299608.html
 * <p>
 * 以i为根节点的树，其左子树由[0, i-1]构成， 其右子树由[i+1, n]构成
 * https://algorithm.yuanbin.me/zh-hans/math_and_bit_manipulation/unique_binary_search_trees.html
 */
public class M96_UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //dp[1] = dp[0] * dp[0]
        dp[1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i + 1] += dp[i - j] * dp[j];
            }
        }
        return dp[n];
    }

    /**
     * 由卡特兰数的递推式还可以推导出其通项公式，即 C(2n,n)/(n+1)，表示在 2n 个数字中任取n个数的方法再除以 n+1
     * 注意!!! 在相乘的时候为了防止整型数溢出，要将结果 res 定义为长整型，最后返回的结果也要计算完再转成 int
     */
    public int numTrees2(int n) {
        if (n <= 1) {
            return 1;
        }
        long res = 1;
        for (int i = n + 1; i <= 2 * n; i++) {
            res = i * res / (i - n);
        }
        return (int) (res / (n + 1));
    }
}
