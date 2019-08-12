package leetcode.easy;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * <p>
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 * <p>
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 * <p>
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class E35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        //注意 h，因为插入元素可能超过数组范围，可能在数组末尾
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l >> 1);
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        new E35_SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 7);
    }
}
