package leetcode.easy;

import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 * <p>
 * Example 2:
 * Input: [1,2,3,4]
 * Output: 24
 * <p>
 * Note:
 * The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 * 求数组三个数最大乘积思路：
 * 想要找到积最大的三个数，那就是要找到最大的三个数，比如[1, 2, 3, 4]中积最大的三个数就是[2, 3, 4]。但这样对么？注意到题目里说到数组元素的值可能是负数，这样情况就不一样了，比如[-4, -5, 1, 2, 3, 4]，这时积最大的三个数就是[-4, -5, 4]（积为80），而非[2, 3, 4]（积为24）。
 * 当考虑到负数后，我们就能发现，积最大的三个数的特征一定是：
 * [正数最大的数, 正数第二大的数, 正数第三大的数] 或 [正数最大的数, 负数最大的数, 负数第二大的数]。
 * 更一般地，其特征一定是：[最大的数, 第二大的数, 第三大的数] 或 [最大的数, 最小的数, 第二小的数]。
 * https://www.polarxiong.com/archives/LeetCode-628-maximum-product-of-three-numbers.html
 */
public class E628_MaximumProductofThreeNumbers {

    //O(N)
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    //O(NlogN)
    public int maximumProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int tmp = nums[n - 1] * nums[n - 2] * nums[n - 3];
        return Math.max(tmp, nums[0] * nums[1] * nums[n - 1]);
    }
}
