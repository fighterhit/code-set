package leetcode.hard;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * @author Fighter.
 */
public class H42_TrappingRainWater {
    /**
     * 法一 递减栈
     * 时间复杂度是 O（n）
     * 空间复杂度：O（n）
     * 参考 https://www.bilibili.com/video/av48995651?from=search&seid=6958612890473333928
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        //维护一个单调递减栈，存放数组索引
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            //遍历到第i个柱子，若比当前栈顶高则违背单调递减
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                //中间凹槽部分
                int top = stack.pop();
                //如果栈重新为空，左边没东西了
                if (stack.isEmpty()) {
                    break;
                }
                int h = Math.min(height[stack.peek()], height[i]) - height[top];
                int dist = i - stack.peek() - 1;
                res += h * dist;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 法二 动态规划前身——O(N*N)
     * 只单独考虑 i 位置的上方能有几格水，假设 arr[i] == 4，若第 i 位左侧所有数（arr[0...i-1]）的最大值为 10，右侧（arr[i+1...N-1]）的最大值为 20，
     * 那么 i 位置上方的水一定能到达高度为 10 的地方，再高的话一定会从左侧流走，所以有 6 格水。右侧同理。
     * 假设 arr[i] == 4，若 i 位左右两侧最大值有一个小于等于 4，那么 i 位上方的水一定会从某侧流走，所以有 0 格水。
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int lMax = 0, rMax = 0;
            for (int j = 0; j < i; j++) {
                lMax = Math.max(lMax, height[j]);
            }
            for (int j = i + 1; j < n; j++) {
                rMax = Math.max(rMax, height[j]);
            }
            res += Math.max(Math.min(lMax, rMax) - height[i], 0);
        }
        return res;
    }

    /**
     * 法三 动态规划，思想同上
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length, res = 0;
        int[] lMax = new int[n], rMax = new int[n];
        lMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            lMax[i] = Math.max(lMax[i - 1], height[i]);
        }
        rMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            res += Math.max(Math.min(lMax[i], rMax[i]) - height[i], 0);
        }
        return res;
    }

    /**
     * 双指针
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * 思路大体同上：设置4个变量, l, r: 分别表示左右指针; lMax, rMax: 分别表示 height[0..l-1] 的最大值和 height[r+1...n-1] 的最大值
     * 1. 若 lMax <= rMax，此时可求 l 上方水量。因为 rMax 是 height[r+1...n-1] 的最大值，而 l 右侧还有一个未遍历区域，所以 l 右侧最大值一定不小于 rMax。
     * lMax 代表 l 左侧最大值，此时又因为 lMax <= rMax，可知左侧最大值 lMax 是 l 位置的瓶颈（类似上面解法的思想，是左右最大值的最小值），故 l 上方水量 = Max(lMax - height[l], 0),
     * 更新左侧最大值 lMax = Max(lMax, height[l])，然后让 l 向右移动 l++
     * 2. 若 lMax > rMax 同理
     */
    public int trap4(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length, res = 0;
        int lMax = height[0], rMax = height[n - 1];
        int l = 1, r = n - 2;
        while (l <= r) {
            if (lMax <= rMax) {
                res += Math.max(lMax - height[l], 0);
                lMax = Math.max(lMax, height[l]);
                l++;
            } else {
                res += Math.max(rMax - height[r], 0);
                rMax = Math.max(rMax, height[r]);
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new H42_TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }
}
