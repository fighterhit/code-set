package leetcode.middle;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 * <p>
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 * <p>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * 注意题目要求时间复杂度小于O(N*N)，空间复杂度为 O(1)
 * 参考 M142_LinkedListCycleII，映射找环法问题，转化为找环的起点
 * 输入数组范围 [1, n]，数组长度为 n+1，注意和 E268_MissingNumber 区别
 * 该题不能用异或，因为不知道数组元素会重复几次，如[2,2,2,2,2]
 * P39_DuplicationInArray，P41_DuplicationInArray,
 */
public class M287_FindtheDuplicateNumber {
    /**
     * 快慢指针法：时间复杂度O(N)，空间复杂度O(1)
     * 假设数组中没有重复，那我们可以做到这么一点，就是将数组的下标和1到n每一个数一对一的映射起来。比如数组是213,则映射关系为0->2, 1->1, 2->3。假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。如果我们从下标为0出发，根据这个函数计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。实际上可以产生一个类似链表一样的序列。
     * 比如在这个例子中有两个下标的序列，0->2->3。如果有重复的话，这中间就会产生多对一的映射，比如数组2131,则映射关系为0->2, {1，3}->1, 2->3。这样，我们推演的序列就一定会有环路了，这里下标的序列是0->2->3->1->1->1->1->...，而环的起点就是重复的数。
     * 所以该题实际上就是找环路起点的题。先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次，直到两个下标再次相同。这时候保持慢下标位置不变，再用一个新的下标从0开始，这两个下标都继续每轮映射一次，当这两个下标相遇时，就是环的起点，也就是重复的数。
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/kuai-man-zhi-zhen-yuan-li-jian-yi-jie-shi-by-damie/
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode/
     */
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /**
     * 二分法：时间复杂度O(NlogN)，空间复杂度O(1)
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/
     */
    public int findDuplicate2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l >> 1);
            int cnt = 0;
            //注意是遍历所有数组元素
            for (int num : nums) {
                if (num <= m) {
                    cnt++;
                }
            }
            //注意判断边界，r = m 时外循环用 l < r，最后返回 l
            //小于等于 m 的数，理论上应该为 m 个，若此时统计得到大于 m 个，则应更改右边界 r = m
            if (cnt > m) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    //参考 P39_DuplicationInArray，这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素放到第 i 个位置上，能通过
    //注意：本题范围是[1, n]，P39_DuplicationInArray 是[0, n-1]
    //题目要求不能修改原数组且空间复杂度为O(1)，但该方法修改了原数组
    public int findDuplicate3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
