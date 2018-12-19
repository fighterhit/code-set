package leetcode.middle;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
 * <p>
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * <p>
 * Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * <p>
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 * <p>
 * Note:
 * The length of the input is in range of [1, 10000]
 *
 * @author Fighter.
 * 把一个升序的数组，分割成几个连续的递增的整数序列。如果能分割，且分割后的每个序列的长度都至少为3，那么认为成功，否则失败
 *
 * 1. 维护两个哈希表 frequency 和 tails. frequency 表记录数组中每个数字出现的次数，而 tails 表则记录以某个数字结尾的连续序列的个数，比如 tails[key] 的值为2，表示以数字 key 为结尾的连续序列共有两个。
 *
 * 2. 第一次扫描数组，把数组中每个数字的出现次数记录到 frequency 表中。
 *
 * 3. 第二次扫描数组，对扫描到的每一个数字 num，如果 tails[num - 1] > 0 ，说明存在以 num - 1 为结尾的连续序列，则把数字 num 添加到该连续序列中：tails[num - 1]--; tails[num]++。可能有人会担心 num可能是另一个连续序列的开始数字而不应该加入到 tails[num - 1] 的连续序列中，但是任意以 num 为开始数组的连续序列都可以与 tails[num - 1] 的连续序列合并：比如 [1, 2, 3] 和 [4, 5, 6] 两个连续序列是可以合并成 [1, 2, 3, 4, 5, 6] 这个大的连续序列的，这对于结果是没有影响的；如果 tails[num - 1] == 0 ，此时我们就需要以 num 为开始数字建立一个新的连续序列。而一个连续序列至少包含 3 个数字，因此还要检查 num + 1 和 num + 2 这两个数字是否存在，如果不存在，则直接 return false ，否则，新建以 num + 2 数字为结尾的连续序列。
 *
 * 4. 如果上述过程能进行到最后，说明所有连续序列都能够找出来，则直接 return true。
 *
 * https://blog.csdn.net/Jack_CJZ/article/details/78306831
 */
public class M659_SplitArrayintoConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> tails = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (freq.get(num) <= 0) {
                continue;
            }
            freq.put(num, freq.get(num) - 1);
            if (tails.getOrDefault(num - 1, 0) > 0) {
                tails.put(num - 1, tails.get(num - 1) - 1);
                tails.put(num, tails.getOrDefault(num, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                tails.put(num + 2, tails.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
