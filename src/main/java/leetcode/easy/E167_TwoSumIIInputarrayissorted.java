package leetcode.easy;


import java.util.*;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * Note:
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */
public class E167_TwoSumIIInputarrayissorted {
    //双指针
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    //该方法和 E1_TwoSum 一样，但没有利用有序这个条件
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]) + 1;
                res[1] = i + 1;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * 延伸：元素重复的排序数组
     * 题目： 给定一个有重复元素的整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数的索引组合有多少对？
     * 例：nums = [1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 6]，target = 6
     * 共有10组解
     * https://www.nowcoder.com/discuss/202345?toCommentId=3188768
     * 对比
     */
    public int twoSum3(int[] numbers, int target) {
        //保存结果数
        int cnt = 0;
        //key 存数值，value 存当前值出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                cnt += map.get(target - numbers[i]);
            }
            map.put(numbers[i], map.getOrDefault(numbers[i], 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        new E167_TwoSumIIInputarrayissorted().twoSum3(new int[]{1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 6}, 6);
    }
}