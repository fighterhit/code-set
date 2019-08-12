package leetcode.hard;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * <p>
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 * Your algorithm should run in O(n) time and uses constant extra space.
 * <p>
 * 把 1 放在数组第一个位置 nums[0]，2 放在第二个位置 nums[1]，即需要把 nums[i] 放在 nums[nums[i] - 1] 上
 * 遍历整个数组，如果 nums[i] != i + 1, 而 nums[i] 为整数且不大于n，另外 nums[i] 不等于 nums[nums[i] - 1] 的话将两者位置调换，如果不满足上述条件直接跳过，最后再遍历一遍数组，如果对应位置上的数不正确则返回正确的数
 * <p>
 * 参考 E268_MissingNumber，P39_DuplicationInArray，M287_FindtheDuplicateNumber
 */
public class H41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //注意边界，要保证为正数且小于数组长度才进行交换
            while (nums[i] > 0 && nums[i] - 1 < n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int a = new H41_FirstMissingPositive().firstMissingPositive(new int[]{1, 2, 3});
        System.out.println(a);
    }
}
