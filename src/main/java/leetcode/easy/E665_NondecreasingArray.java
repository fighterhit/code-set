package leetcode.easy;

/**
 * 判断一个数组能不能只修改一个数就成为非递减数组。
 * 在出现 nums[i] < nums[i - 1] 时，需要考虑的是应该修改数组的哪个数，使得本次修改能使 i 之前的数组成为非递减数组，并且 不影响后续的操作 。优先考虑令 nums[i - 1] = nums[i]，因为如果修改 nums[i] = nums[i - 1] 的话，那么 nums[i] 这个数会变大，就有可能比 nums[i + 1] 大，从而影响了后续操作。还有一个比较特别的情况就是 nums[i] < nums[i - 2]，只修改 nums[i - 1] = nums[i] 不能使数组成为非递减数组，只能修改 nums[i] = nums[i - 1]。
 * https://www.cnblogs.com/grandyang/p/7565424.html
 */
public class E665_NondecreasingArray {
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int n = nums.length, cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                if (cnt == 0) {
                    return false;
                }
                if (i > 1 && nums[i] < nums[i - 2]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i - 1] = nums[i];
                }
                cnt--;
            }
        }
        return true;
    }
}
