package leetcode.hard;

//可过牛客 P82_MinNumberInRotatedArray
public class H154_FindMinimuminRotatedSortedArrayII {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1, mid = 0;
        while (l < r) {
            // >> 1 在leetcode 通不过
            mid = l + (r - l) / 2;
//            mid = l + (r - l) >> 1;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            }
            // When num[mid] == num[hi]
            //we couldn't sure the position of minimum in mid's left or right, so just let upper bound reduce one.
            else {
                r--;
            }
        }
        return nums[l];
    }
}
