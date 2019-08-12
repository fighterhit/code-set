package leetcode.middle;

import java.util.*;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * Example:
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 排列、组合、子集一般方法(模板)
 * https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
 */
public class M77_Combinations {
    List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new LinkedList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        //从 1 开始
        generateCombinations(n, k, 1, new LinkedList<>());
        return res;
    }

    private void generateCombinations(int n, int k, int start, LinkedList<Integer> es) {
        if (es.size() == k) {
            res.add(new LinkedList<>(es));
            return;
        }

        //注意此处的剪枝优化
        //还有 k-c.size() 个空位，所以 [i...n] 至少要有 k-c.size() 个元素，i 最多为 n - (k-c.size()) + 1
        for (int i = start; i <= n - (k - es.size()) + 1; i++) {
            es.add(i);
            generateCombinations(n, k, i + 1, es);
            es.removeLast();
        }
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        boolean[] v = new boolean[n + 1];
        helper(res, tmp, n, k, 1, v);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int n, int k, int start, boolean[] v) {
//        if (tmp.size() == k) {
        if (0 == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        //设置循环起始点已经能保证元素不会重复，因此可以不用 boolean[] 标记
        //注意，排列和组合的区别，排列从 0 开始，需要数组标记，组合根据情况从 start 或 start + 1 开始，不需要
//        for (int i = start; i <= n; i++) {
        //优化，个数一定：n - k + 1
        for (int i = start; i <= n - k + 1; i++) {
            if (v[i]) {
                continue;
            }
            tmp.add(i);
            v[i] = true;
//            helper(res, tmp, n, k, i + 1, v);
            helper(res, tmp, n, k - 1, i + 1, v);
            v[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }
}
