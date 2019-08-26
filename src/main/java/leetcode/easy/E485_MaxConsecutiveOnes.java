package leetcode.easy;

public class E485_MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, res = 0;
        for (int num : nums) {
            cnt = num == 0 ? 0 : cnt + 1;
            res = Math.max(res, cnt);
        }
        return res;
    }
}
