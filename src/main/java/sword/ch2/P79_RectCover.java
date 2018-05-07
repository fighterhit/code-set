package sword.ch2;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * @author Fighter Created on 2018/4/30.
 */
public class P79_RectCover {
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int a = 1, b = 2, c = -1;
        for (int i = 3; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
