package leetcode.middle;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class M31_NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = nums.length - 2, j = nums.length - 1;
        //先找到第一个升序点（从右往左看），如 [1,3,2] -> 1
        //如果当前元素一直比后个元素大，则往下找
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //判断是否整个数组是降序的, >= 0 说明找到的 i 在数组中，i < 0说明整个数组都是降序的
        if (i >= 0) {
            //从数组最后元素开始找比 i 位置元素大的交换
            while (j > i && nums[i] >= nums[j]) {
                j--;
            }
            //交换值
            swap(nums, i, j);
        }
        //交换完值后，将i位置后原来降序的序列改为升序
        reverse(nums, i + 1, nums.length - 1);

    }

    /**
     * 这道题让我们求下一个排列顺序，由题目中给的例子可以看出来，如果给定数组是降序，则说明是全排列的最后一种情况，则下一个排列就是最初始情况。我们再来看下面一个例子，有如下的一个数组
     * 1　　2　　7　　4　　3　　1
     * <p>
     * 下一个排列为：
     * 1　　3　　1　　2　　4　　7
     * 那么是如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后我们再从后往前找第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：
     * 1　　2　　7　　4　　3　　1
     * <p>
     * 1　　2　　7　　4　　3　　1
     * <p>
     * 1　　3　　7　　4　　2　　1
     * <p>
     * 1　　3　　1　　2　　4　　7
     *
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length, i = n - 2, j = n - 1;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                for (; j >= 0; j--) {
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                        reverse(nums, i + 1, n - 1);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0, n - 1);
    }


    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        new M31_NextPermutation().nextPermutation(new int[]{5, 1, 1});
    }
}
