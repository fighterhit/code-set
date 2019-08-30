package algorithm;

import java.util.Random;

/**
 * Fisher–Yates shuffle
 * 用于随机打乱一组数，并且时间复杂度为 O(N)。
 * 算法的基本思想是，每次从一组数中随机选出一个数，然后与最后一个数交换位置，并且不再考虑最后一个数。
 */
public class Shuffle {
    public static void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = nums.length - 1; i >= 0; i--) {
            //随机得到 [0...i] 之间的某个索引
            int j = random.nextInt(i + 1);
            swap(nums, i, j);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
