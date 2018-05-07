package sword.ch2;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * @author Fighter Created on 2018/4/30.
 */
public class P77_JumpFloor {
    public int JumpFloor(int target) {
        if (target <= 1) {
            return target;
        }
        int a = 1, b = 1, c = -1;
        for (int i = 2; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
