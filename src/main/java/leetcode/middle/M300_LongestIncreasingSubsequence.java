package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 */
public class M300_LongestIncreasingSubsequence {

    //O(N^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    /**
     * 可以使用二分查找将时间复杂度降低为 O(NlogN)。
     * 定义一个 tails 数组，其中 tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素。对于一个元素 x，
     * 如果它大于 tails 数组所有的值，那么把它添加到 tails 后面，表示最长递增子序列长度加 1；
     * 如果 tails[i-1] < x <= tails[i]，那么更新 tails[i] = x。
     * 例如对于数组 [4,3,6,5]，有：
     * tails      len      num
     * []         0        4
     * [4]        1        3
     * [3]        1        6
     * [3,6]      2        5
     * [3,5]      2        null
     * 可以看出 tails 数组保持有序，因此在查找 Si 位于 tails 数组的位置时就可以使用二分查找。
     */
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = binarySearch(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int binarySearch(int[] nums, int len, int num) {
        int l = 0, r = len;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 先建立一个空的 dp 数组，然后开始遍历原数组，对于每一个遍历到的数字，用二分查找法在 dp 数组找第一个不小于它的数字
     * 如果这个数字不存在，那么直接在 dp 数组后面加上遍历到的数字；如果存在，则将这个数字更新为当前遍历到的数字，最后返回 dp 数组的长度即可。
     * 注意：ret 的值可能不是一个真实的 LIS，比如若输入数组 nums 为 {4, 2， 4， 5， 3， 7}，那么算完后的 ret 为 {2， 3， 5， 7}，可以发现它不是一个原数组的 LIS，只是长度相等而已，千万要注意这点。
     * https://www.cnblogs.com/grandyang/p/4938187.html
     */
    public static int lengthOfLIS3(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        for (int num : nums) {
            int l = 0, r = ret.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (ret.get(mid) >= num) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            //越界，说明在ret列表中没找到，直接加入列表
            if (l >= ret.size()) {
                ret.add(num);
            } else {
                ret.set(l, num);
            }
        }
        return ret.size();
    }

    public static void main(String[] args) {
        lengthOfLIS3(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        lengthOfLIS3(new int[]{4, 2, 4, 5, 3, 7});
    }
}
