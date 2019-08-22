package leetcode.middle;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * <p>
 * 循环数组找下一个比当前数大的数字，默认是-1，对比 M739_DailyTemperatures
 */
public class M503_NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return null;
        }
        int n = nums.length;
        int[] ret = new int[n];
        //注意初始化为 -1
        Arrays.fill(ret, -1);
        Stack<Integer> indexS = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            //注意 i % n ！！！
            int num = nums[i % n];
            while (!indexS.isEmpty() && nums[indexS.peek()] < num) {
                int index = indexS.pop();
                ret[index] = num;
            }
            indexS.push(i % n);
        }
        return ret;
    }

    public static void main(String[] args) {
        new M503_NextGreaterElementII().nextGreaterElements(new int[]{1, 2, 3, 4, 2, 0});
    }
}
