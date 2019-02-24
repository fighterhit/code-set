package sword.ch2;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author Fighter Created on 2018/4/30.
 */
public class P82_MinNumberInRotatedArray {
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0, right = array.length - 1, mid = 0;

        //注意 left < right
        while (left < right) {
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = left + ((right - left) >> 1);

            // 若序列类似：10111  11101，则即 array[left] == array[mid] == array[right]，需要顺序查找
            if (array[left] == array[mid] && array[right] == mid) {
                return minNumber(array, left, right);
            }

            if (array[mid] >= array[left]) {
                left = mid;
            } else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        return array[mid];
    }

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
    public int minNumberInRotateArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0, right = array.length - 1, mid = 0;

        //注意 left < right
        while (left < right) {
            mid = left + (right - left) >> 1;
            if (array[mid] == array[left] && array[mid] == array[right]) {
                return minNumber(array, left, right);
            }
            if (array[mid] <= array[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
//        return array[right];
        return array[left];
    }
}
