package leetcode.easy;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 * <p>
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * <p>
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * n级台阶的走法 = 先走一级后，n-1级台阶的走法 + 先走两级后，n-2级台阶的走法。即 f(n) = f(n-1)+f(n-2)
 */
public class E70_ClimbingStairs {
    //递归超时
    public int climbStairs(int n) {
        if (n <= 0 || n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    //递归超时
    public int climbStairs2(int n) {
        if (n <= 0 || n == 1 || n == 2) {
            return n;
        }
        int a = 1, b = 2, res = 0;
        for (int i = 3; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }
}
