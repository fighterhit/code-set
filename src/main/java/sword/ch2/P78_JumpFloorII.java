package sword.ch2;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 通过列举得到等比数列： f(n) = 2^n - 1;
 *
 * @author Fighter Created on 2018/4/30.
 */
public class P78_JumpFloorII {
    public int JumpFloorII(int target) {
        int[] step = new int[target + 1];
        step[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                step[i]+=step[j];
            }
        }
        return step[target];
    }
}
