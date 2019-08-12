package leetcode.hard;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * Example:
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 * 此题是之前那道的 H84_LargestRectangleinHistogram 的扩展，这道题的二维矩阵每一层向上都可以看做一个直方图，输入矩阵有多少行，就可以形成多少个直方图，对每个直方图都调用 H84_LargestRectangleinHistogram 中的方法，就可以得到最大的矩形面积。
 * 这道题唯一要做的就是将每一层都当作直方图的底层，并向上构造整个直方图，由于题目限定了输入矩阵的字符只有 '0' 和 '1' 两种，所以处理起来也相对简单。方法是，对于每一个点，如果是 ‘0’，则赋0，如果是 ‘1’，就赋之前的 height 值加上1。
 * https://www.cnblogs.com/grandyang/p/4322667.html
 */
public class H85_MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] line1Cnt = new int[n];
        int maxArea = 0;
        //自上而下算连续1的个数，当出现 0 时即连续的 1 出现中断，此时置 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                line1Cnt[j] = matrix[i][j] == '0' ? 0 : line1Cnt[j] + 1;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(line1Cnt));
        }
        return maxArea;
    }

    //H84_LargestRectangleinHistogram
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = 0;
        //保存数组位置
        //递减栈：自栈顶到栈底递减——求小于且离当前元素最近的左、右两侧元素位置
        Stack<Integer> stack = new Stack<>();
        //依次计算每个位置形成最大矩形面积
        for (int i = 0; i < heights.length; i++) {
            //若当前 i 位置元素小于等于栈顶元素则弹出栈顶元素
            while (stack.size() > 0 && heights[i] <= heights[stack.peek()]) {
                //栈顶元素的位置
                int popIndex = stack.pop();
                //弹栈后的栈顶元素
                int k = stack.isEmpty() ? -1 : stack.peek();
                //计算 popIndex 位置处向左、右扩展后最大矩形面积
                int area = (i - k - 1) * heights[popIndex];
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }

        //计算栈剩余元素的最大矩形面积，注意此时 i == heights.length，heights[i] 可当做无限小的数参与运算
        while (stack.size() > 0) {
            int popIndex = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int area = (heights.length - k - 1) * heights[popIndex];
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        new H85_MaximalRectangle().maximalRectangle(new char[][]{{'0', '1'}, {'1', '0'}});
    }
}
