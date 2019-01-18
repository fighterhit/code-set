package leetcode.middle;

import java.util.LinkedList;
import java.util.List;

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
}
