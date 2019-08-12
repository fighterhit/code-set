package leetcode.middle;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * 这是一个动态规划问题，通常解决并理解一个动态规划问题需要以下 4 个步骤：
 * 1. 利用递归回溯解决问题
 * 2. 利用记忆表优化（自顶向下的动态规划）
 * 3. 移除递归的部分（自底向上的动态规划）
 * 4. 使用技巧减少时间和空间复杂度
 * https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode/
 * <p>
 * 参考 H45_JumpGameII
 */
public class M55_JumpGame {
    /**
     * 法1：回溯
     * 时间复杂度：O(2^n) 最多有 2^n 种从第一个位置到最后一个位置跳跃方式，其中 n 是数组 nums 的元素个数，完整的证明见附录 A。
     * 空间复杂度：O(n)，回溯法只需要栈的额外空间。
     * 这是一个低效的解决方法。模拟从第一个位置跳到最后位置的所有方案。从第一个位置开始，模拟所有可以跳到的位置，然后从当前位置重复上述操作，当没有办法继续跳的时候，就回溯。
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return helper(0, nums);
    }

    private boolean helper(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        //优化：从右到左的检查 nextposition ，理论上最坏的时间复杂度复杂度是一样的。但实际对于一些简单场景，这个代码可能跑得更快一些。
        //直觉上，每次选择最大的步数去跳跃，这样就可以更快的到达终点。
        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
//        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (helper(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 法2：自顶向下的动态规划——记忆化搜索
     * 时间复杂度：O(n^2)，数组中的每个元素假设为 i，需要搜索右边相邻的 nums[i] 个元素查找是否有 GOOD 的坐标。 nums[i] 最多为 n，n 是 nums 数组的大小。
     * 空间复杂度：O(2n)=O(n)O(2n)=O(n)，第一个 n 是栈空间的开销，第二个 n 是记忆表的开销。
     */
    enum Flag {
        BAD, GOOD, UNKNOWN
    }

    Flag[] memo;

    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        memo = new Flag[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = Flag.UNKNOWN;
        }
        memo[nums.length - 1] = Flag.GOOD;
        return helper(0, nums);
    }

    private boolean helper2(int position, int[] nums) {
        if (memo[position] != Flag.UNKNOWN) {
            return memo[position] == Flag.GOOD;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        //优化：从右到左的检查 nextposition ，理论上最坏的时间复杂度复杂度是一样的。但实际对于一些简单场景，这个代码可能跑得更快一些。
        //直觉上，每次选择最大的步数去跳跃，这样就可以更快的到达终点。
        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
            if (helper(nextPosition, nums)) {
                memo[position] = Flag.GOOD;
                return true;
            }
        }
        memo[position] = Flag.BAD;
        return false;
    }

    /**
     * 法3：自底向上的动态规划
     * 时间复杂度：O(n^2)，数组中的每个元素假设为 i，需要搜索右边相邻的 nums[i] 个元素查找是否有 GOOD 的坐标。 nums[i] 最多为 n，n 是 nums 数组的大小。
     * 空间复杂度：O(n)，记忆表的存储开销。
     * <p>
     * 自底向上和自顶向下动态规划的区别就是 消除了回溯。
     * 在实际使用中，自底向下的方法有更好的时间效率因为我们不再需要栈空间，可以节省很多缓存开销。更重要的事，这可以让之后更有优化的空间。回溯通常是通过 反转动态规划 的步骤来实现的。
     * 这是由于每次只会向右跳动，意味着如果从右边开始动态规划，每次查询右边节点的信息，都是已经计算过了的，不再需要额外的递归开销，因为每次在 memo 表中都可以找到结果。
     */
    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        memo = new Flag[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = Flag.UNKNOWN;
        }
        memo[nums.length - 1] = Flag.GOOD;
        for (int i = nums.length - 2; i >= 0; i--) {
            int futthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= futthestJump; j++) {
                if (memo[j] == Flag.GOOD) {
                    memo[i] = Flag.GOOD;
                }
            }
        }
        return memo[0] == Flag.GOOD;
    }

    /**
     * 法4：贪心
     * 当把代码改成自底向上的模式，我们会有一个重要的发现，从某个位置出发，只需要找到第一个标记为 GOOD 的坐标（由跳出循环的条件可得），也就是说找到最左边的那个坐标。如果我们用一个单独的变量来记录最左边的 GOOD 位置，我们就可以避免搜索整个数组，进而可以省略整个 memo 数组。
     * 从右向左迭代，对于每个节点我们检查是否存在一步跳跃可以到达 GOOD 的位置（currPosition + nums[currPosition] >= leftmostGoodIndex）。如果可以到达，当前位置也标记为 GOOD ，同时，这个位置将成为新的最左边的 GOOD 位置，一直重复到数组的开头，如果第一个坐标标记为 GOOD 意味着可以从第一个位置跳到最后的位置。
     * 模拟一下这个操作，对于输入数组 nums = [9, 4, 2, 1, 0, 2, 0]，我们用 G 表示 GOOD，用 B 表示 BAD 和 U 表示 UNKNOWN。我们需要考虑所有从 0 出发的情况并判断坐标 0 是否是好坐标。由于坐标 1 是 GOOD，我们可以从 0 跳到 1 并且 1 最终可以跳到坐标 6，所以尽管 nums[0] 可以直接跳到最后的位置，我们只需要一种方案就可以知道结果。
     * 时间复杂度：O(n)，只需要访问 nums 数组一遍，共 n 个位置，n 是 nums 数组的长度。
     * 空间复杂度：O(1)，不需要额外的空间开销。
     */
    public boolean canJump4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int leftGoodIndex = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= leftGoodIndex) {
                leftGoodIndex = i;
            }
        }
        return leftGoodIndex == 0;
    }

    /**
     * maxReach 指能到达最远位置，只能遍历在 maxReach 范围内的下标值即 i
     * https://www.bilibili.com/video/av10326471?from=search&seid=1052784715278234357
     */

    public boolean canJump5(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int maxReach = 0;
        for (int i = 0; i < nums.length && i <= maxReach; i++) {
            maxReach = Math.max(i + nums[i], maxReach);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 1, 4};
        int[] arr2 = new int[]{3, 2, 1, 0, 4};
        boolean b = new M55_JumpGame().canJump2(arr);
        System.out.println(b);
        b = new M55_JumpGame().canJump2(arr2);
        System.out.println(b);
    }
}
