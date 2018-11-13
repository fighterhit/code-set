package leetcode.middle;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * 3路快排思路[l..lt] [lt+1...gt-1] [gt...r]
 */
public class M75_SortColors {
    public void sortColors(int[] nums) {
        int lt = -1, gt = nums.length;
        for (int i = 0; i < gt; i++) {
            if (nums[i] == 0) {
                nums[i] = 1;
                nums[++lt] = 0;
            } else if (nums[i] == 1) {
                continue;
            } else {
                nums[i--] = nums[--gt];
                nums[gt] = 2;
            }
        }
        //注意和上面边界区别：[l..lt） [lt...gt-1] [gt...r]
        /*int lt = 0, gt = nums.length;
        for (int i = 0; i < gt; i++) {
            if (nums[i] == 0) {
                nums[i] = 1;
                nums[lt++] = 0;
            } else if (nums[i] == 1) {
                continue;
            } else {
                nums[i--] = nums[--gt];
                nums[gt] = 2;
            }
        }*/
    }
}
