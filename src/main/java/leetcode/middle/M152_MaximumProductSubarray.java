package leetcode.middle;

public class M152_MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE, res = 1;
        for (int i = 0; i < nums.length; i++) {
            if ((res > 0 && nums[i] > 0) || (res < 0 && nums[i] < 0)) {
                res *= nums[i];
            }








            max = Math.max(max, res);
        }
        return max;
    }
}
