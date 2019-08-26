package leetcode.easy;

/**
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * 示例 1:
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 * <p>
 * 参考 P39_DuplicationInArray
 */
public class E645_SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            //对于每一个位置，都将该位置应放的元素放过来，直到遇到该位置放了重复元素停止，继续扫描下一个元素
//            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
//                swap(nums, i, nums[i] - 1);
//            }
            while (i + 1 != nums[i]) {
                if (nums[nums[i] - 1] == nums[i]) {
                    res[0] = nums[i];
                    break;
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                res[1] = i + 1;
            }
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
