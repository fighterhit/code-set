package leetcode.middle;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 */
public class M80_RemoveDuplicatesfromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int i = 1;
        for (int j = 2; j < nums.length; j++) {
            if (nums[j] != nums[i] || nums[j] != nums[i - 1]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 用一个变量count来记录还允许有几次重复，count初始化为1.
     * 如果出现过一次重复，则count递减1，那么下次再出现重复，快指针直接前进一步，如果这时候不是重复的，则count恢复1，由于整个数组是有序的，所以一旦出现不重复的数，则一定比这个数大，此数之后不会再有重复项
     * https://www.cnblogs.com/grandyang/p/4329295.html
     */
    public int removeDuplicates2(int[] nums) {
        int pre = 0, cur = 1, count = 1;
        for (; cur < nums.length; ) {
            if (nums[pre] == nums[cur] && count == 0) {
                cur++;
            } else {
                if (nums[pre] == nums[cur]) {
                    count--;
                } else {
                    count = 1;
                }
                pre++;
                nums[pre] = nums[cur];
                cur++;
            }
        }
        return pre + 1;
    }

}
