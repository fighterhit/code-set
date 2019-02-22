package sword.ch2;

/**
 * 长度为 n+1 的数组中所有数字范围为 1~n，不修改原数组找重复数字
 *
 * @author Fighter.
 */
public class P41_DuplicationInArray {
    public static int duplicate(int numbers[], int length) {
        if (numbers == null || numbers.length < 2) {
            return -1;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] <= 0 || numbers[i] >= length) {
                return -1;
            }
        }

        int start = 1, end = length - 1;

        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            //求在[start,end]内的元素个数
            int count = countRange(numbers, length, start, mid);
            //判断首尾指针相等时元素个数是否为 1，不为 1 则返回start，否则 break 退出循环
            if (start == end) {
                if (count > 1) {
                    return start;
                }
                break;
            }
            //说明前半部分元素有重复
            if (count > mid - start + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    //求在[start, end]内的元素个数
    private static int countRange(int[] numbers, int length, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] >= start && numbers[i] <= end) {
                count++;
            }
        }
        return count;
    }

    private static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
