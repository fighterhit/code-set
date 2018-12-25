package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 将所有元素放入查找表，查看target-a是否存在即可
 * 遍历每个元素v，只将v前元素放入查找表防止全部一次性放入查找表时相同元素被覆盖
 * 不能先初始化哈希表，否则无法排除两个相同的元素相加为 target 的情况，比如target是4，遍历到了一个2，那么另外一个2不能是之前那个2
 */
public class E1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
