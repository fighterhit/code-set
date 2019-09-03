package leetcode.middle;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 * <p>
 * 令 index 为 Single Element 在数组中的位置。如果 m 为偶数，并且 m + 1 < index，那么 nums[m] == nums[m + 1]；m + 1 >= index，那么 nums[m] != nums[m + 1]。
 * 从上面的规律可以知道，如果 nums[m] == nums[m + 1]，那么 index 所在的数组位置为 [m + 2, h]，此时令 l = m + 2；如果 nums[m] != nums[m + 1]，那么 index 所在的数组位置为 [l, m]，此时令 h = m。
 * 因为 h 的赋值表达式为 h = m，那么循环条件也就只能使用 l < h 这种形式。
 */
public class M540_SingleElementinaSortedArray {

    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l >> 1);
            if (m % 2 == 1) {
                //保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
                m--;
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    public int singleNonDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            //判断当前 mid 奇偶位
            //奇数位则判断与前面相邻元素是否相同
            if (mid % 2 == 1) {
                //相同则增大左边界，否则减小右边界
                if (nums[mid] == nums[mid - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            //偶数位则判断是否和后面元素相同
            else {
                //相同则增大左边界，注意直接+2；否则减小有边界
                if (nums[mid] == nums[mid + 1]) {
                    l = mid + 2;
                } else {
                    r = mid - 1;
                }
            }
        }
        return nums[l];

    }

    //O(N)，注意题目要求 O(logN)
    public int singleNonDuplicate3(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }
}
