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
    /**
     * 参考 QuickSort3Ways 三路快排
     * arr[l+1...lt]<v, arr[lt+1...i)==v, arr[gt...r]>v
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //注意初始化，三路快排中 lt 初始为 l，因为填充元素是从 l + 1 开始，最后会将一开始的 pivot 交换到合适位置。
        //这里将 lt 初始化为 -1，因为 pivot 已知为1，填充元素是从 0 开始
        int lt = -1, gt = nums.length, i = 0;
        while (i < gt) {
            if (nums[i] == 0) {
                swap(nums, lt + 1, i);
                i++;
                lt++;
            } else if (nums[i] == 2) {
                swap(nums, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        //因为只有3种元素，以 1 位pivot，左侧全为 0 右侧全为 2，因此最后无须递归
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void sortColors2(int[] nums) {
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
