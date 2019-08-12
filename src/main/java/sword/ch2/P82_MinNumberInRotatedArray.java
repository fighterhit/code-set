package sword.ch2;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author Fighter Created on 2018/4/30.
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/%E5%89%91%E6%8C%87%20Offer%20%E9%A2%98%E8%A7%A3%20-%2010~19.md#11-%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97
 * 将旋转数组对半分可以得到一个包含最小元素的新旋转数组，以及一个非递减排序的数组。问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
 * <p>
 * 元素不重复：
 * 通过修改二分查找算法进行求解（l 代表 low，m 代表 mid，h 代表 high）：
 * 当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，此时令 h = m；
 * 否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1。
 * <p>
 * 元素重复：
 * 如果数组元素允许重复，会出现一个特殊的情况：nums[l] == nums[m] == nums[h]，此时无法确定解在哪个区间，需要切换到顺序查找。
 * 例如对于数组 {1,1,1,0,1}，l、m 和 h 指向的数都为 1，此时无法知道最小数字 0 在哪个区间。
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 * <p>
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class P82_MinNumberInRotatedArray {

    private int minNumber(int[] array, int left, int right) {
        int min = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * 当 nums[mid] <= nums[right] 的情况下，说明解在 [left, mid] 之间，此时令 right = mid；
     * 否则解在 [mid + 1, right] 之间，令 left = mid + 1。
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0, right = array.length - 1, mid = 0;

        //注意 left < right
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (array[mid] == array[left] && array[mid] == array[right]) {
                return minNumber(array, left, right);
            }
            if (array[mid] <= array[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }
}
