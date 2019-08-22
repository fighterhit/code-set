package leetcode.middle;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * <p>
 * 默认是0，对比 M503_NextGreaterElementII
 */
public class M739_DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return null;
        }
        int[] ret = new int[T.length];
        Stack<Integer> indexS = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!indexS.isEmpty() && T[indexS.peek()] < T[i]) {
                int index = indexS.pop();
                ret[index] = i - index;
            }
            indexS.push(i);
        }
        return ret;
    }
}
