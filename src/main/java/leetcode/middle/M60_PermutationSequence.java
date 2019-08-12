package leetcode.middle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 * https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
 * https://www.cnblogs.com/jdneo/p/5305212.html
 * https://www.cnblogs.com/grandyang/p/4358678.html
 * 那么我们就可以找出规律了：k / (n - 1)! 算出所求数在第几组，k % (n - 1)! 算出在该组第几个（也即除了当前位后面排列数的第几个）
 * a1 = k / (n - 1)!
 * k1 = k
 * a2 = k1 / (n - 2)!
 * k2 = k1 % (n - 2)!
 * ...
 * an-1 = kn-2 / 1!
 * kn-1 = kn-2 % 1!
 * an = kn-1 / 0!
 * kn = kn-1 % 0!
 */
public class M60_PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder("123456789");
        //先算出 1 ~ n-1 的所有排列数结果
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * i;
        }
        k--;
        for (int i = n; i >= 1; i--) {
            int groupIndex = k / dp[i - 1];
            k = k % dp[i - 1];
            res.append(sb.charAt(groupIndex));
            sb.deleteCharAt(groupIndex);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String r = new M60_PermutationSequence().getPermutation(3, 3);
        System.out.println(r);
    }
}
