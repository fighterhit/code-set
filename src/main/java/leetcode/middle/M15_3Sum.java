package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * 注意，结果不能重复！！！
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 先排序，然后固定一个数，在后面数组中用双指针找。注意三个指针都要避免重复！！！
 * 优化：当遍历到的第一个数大于 0 即可停止
 */
public class M15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        //先排序!!!
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //与下面等价
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //每个元素只计算一次，连着相同的元素跳过，防止结果重复
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //注意这里双指针也要避免重复
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new M15_3Sum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}
