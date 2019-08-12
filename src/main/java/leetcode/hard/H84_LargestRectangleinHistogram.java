package leetcode.hard;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * <p>
 * https://www.cnblogs.com/grandyang/p/4322653.html
 */
public class H84_LargestRectangleinHistogram {
    /**
     * 遍历数组，每找到一个局部峰值（只要当前的数字大于后面的一个数字，那么当前数字就看作一个局部峰值，跟前面的数字大小无关），然后向前遍历所有的值，算出共同的矩形面积，每次对比保留最大值。
     * 这里再说下为啥要从局部峰值处理，看题目中的例子，局部峰值为 2，6，3，我们只需在这些局部峰值出进行处理，为啥不用在非局部峰值处统计呢，这是因为非局部峰值处的情况，后面的局部峰值都可以包括，比如1和5，由于局部峰值6是高于1和5的，所有1和5能组成的矩形，到6这里都能组成，并且还可以加上6本身的一部分组成更大的矩形，那么就不用费力气去再统计一个1和5处能组成的矩形了。
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (i + 1 < heights.length && heights[i] <= heights[i + 1]) {
                continue;
            }
            int minH = heights[i];
            //从当前 i 开始而不是 i- 1，包括右边界，如只有一个元素的用例 [1]，返回 1 才对
            for (int j = i; j >= 0; j--) {
                minH = Math.min(minH, heights[j]);
                res = Math.max(res, minH * (i - j + 1));
            }
        }
        return res;
    }

    /**
     * 单调递减栈：代码面试之南2ed P20
     * https://www.cnblogs.com/grandyang/p/8887985.html
     */
    public int largestRectangleArea2(int[] heights) {
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
}
