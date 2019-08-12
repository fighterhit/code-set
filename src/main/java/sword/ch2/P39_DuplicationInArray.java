package sword.ch2;

/**
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * <p>
 * 这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素放到第 i 个位置上。
 * position-0 : (2,3,1,0,2,5) // 2 <-> 1
 * (1,3,2,0,2,5) // 1 <-> 3
 * (3,1,2,0,2,5) // 3 <-> 0
 * (0,1,2,3,2,5) // already in position
 * position-1 : (0,1,2,3,2,5) // already in position
 * position-2 : (0,1,2,3,2,5) // already in position
 * position-3 : (0,1,2,3,2,5) // already in position
 * position-4 : (0,1,2,3,2,5) // nums[i] == nums[nums[i]], exit
 * <p>
 * 代码尽管有一个两重for循环，但每个数字最多只要交换两次就能找到属于它自己的位置，因此总的时间复杂度为 O(n)，空间复杂度为 O(1)
 */
public class P39_DuplicationInArray {

    public static boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > length - 1) {
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            while (i != numbers[i]) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i, numbers[i]);
            }
        }
        return false;
    }

    private static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
