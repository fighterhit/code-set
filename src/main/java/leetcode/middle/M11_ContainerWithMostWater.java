package leetcode.middle;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class M11_ContainerWithMostWater {

    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            //当i, j由初始情况变化时，j-i 必定会减少，现在要使 s 增大，则要使min(height[i], height[j])变大，显然其值由较小值决定，因此应该移动较小值来试探下一个可能的s较大值。
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    public int maxArea2(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int tmpArea = area(height, i, j);
                maxArea = maxArea > tmpArea ? maxArea : tmpArea;
            }
        }
        return maxArea;
    }

    private int area(int[] height, int i, int j) {
        return Math.min(height[i], height[j]) * (j - i);
    }
}
