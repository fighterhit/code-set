package leetcode.hard;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * You can assume that you can always reach the last index.
 * <p>
 * 参考 M55_JumpGame
 */
public class H45_JumpGameII {
    //超时
    public int jump(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    //从一个位置跳到它能跳到的最远位置之间的都只需要一步
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * 从一个位置跳到它能跳到的最远位置之间的都只需要一步!
     * 所以,如果一开始都能跳到,后面再跳到的肯定步数要变多!
     * https://leetcode-cn.com/problems/jump-game-ii/solution/tan-xin-suan-fa-by-powcai/
     */
    public int jump2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        //dp[i] 表示到 i 位置的最少步数
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j >= 0; j--) {
                if (i + j >= nums.length - 1) {
                    return dp[i] + 1;
                } else {
                    if (dp[i + j] == 0) {
                        dp[i + j] = dp[i] + 1;
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * res 表示当前跳了多少步，cur 表示 res 步下能到达最远位置，next 表示再多跳一步能到达最远位置
     * 参考：M55_JumpGame 中的 canJump5，每次更新到达的最远位置
     * 代码面试指南2ed P247
     */
    public int jump3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int steps = 0, cur = 0, next = 0;
        for (int i = 0; i < nums.length; i++) {
            //在当前 steps 步到达不了 i，因此需要多跳一步 steps++，并且 cur 更新为 next
            if (cur < i) {
                steps++;
                cur = next;
            }
            //更新下一步能到达的最远位置
            next = Math.max(next, i + nums[i]);
        }
        return steps;
    }


    /**
     * 参考：M55_JumpGame 中的 canJump5，每次更新到达的最远位置
     * https://www.bilibili.com/video/av10326471?from=search&seid=1052784715278234357
     */
    public int jump4(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        //index 遍历的索引、curMax 当前steps步能达到最远位置，nextMax 下一步能达到最远位置，steps 步数
        int index = 0, curMax = 0, nextMax = 0, steps = 0;
        //假设存在不合法输入，即到达不了数组尾，此时 index 会大于 curMax 从而跳出外层循环
        while (index <= curMax) {
            //在当前每一步去寻找下一步能到达的最远位置
            while (index <= curMax) {
                nextMax = Math.max(nextMax, nums[index] + index);
                index++;
            }
            curMax = nextMax;
            steps++;
            if (curMax >= nums.length - 1) {
                return steps;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 1, 4};
        int[] arr2 = new int[]{3, 2, 1, 0, 4};
        int b = new H45_JumpGameII().jump(arr);
        System.out.println(b);
        b = new H45_JumpGameII().jump3(arr2);
        System.out.println(b);
    }
}
