package leetcode.easy;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * <p>
 * 注意:
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 */
public class E605_CanPlaceFlowers {

    /**
     * 输入一个01数组，其中1表示已经放了花，0表示可以放花的位置，但是有个限制条件是不能有相邻的花。
     * 那么来看如果是一些简单的例子：
     * 1. 如果有3个连续的零，000，能放几盆花呢，其实是要取决约左右的位置的；
     * 2. 如果是10001，那么只能放1盆，如果左右是边界的花，那么就能放两盆，101。
     * <p>
     * 所以如果我们想通过计算连续 0 的个数，然后直接算出能放花的个数，就必须要对边界进行处理。
     * <p>
     * 处理方法：
     * 如果首位置是0，那么前面再加上个0，如果末位置是0，就在最后面再加上个0。
     * 这样处理之后我们就默认连续0的左右两边都是1了，这样如果有k个连续0，那么就可以通过(k-1)/2来快速计算出能放的花的数量
     * https://www.cnblogs.com/grandyang/p/6983982.html
     */

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int[] arr = new int[len + 2];
        arr[0] = 0;
        arr[len + 1] = 0;
        for (int i = 1; i <= len; i++) {
            arr[i] = flowerbed[i - 1];
        }
        for (int i = 1; i <= len; i++) {
            if (n == 0)
                return true;
            if (arr[i - 1] + arr[i] + arr[i + 1] == 0) {
                n--;
                i++;
            }
        }
        return n <= 0;
    }

    //遍历花床，如果某个位置为 0，我们就看其前面一个和后面一个位置的值，注意处理首位置和末位置的情况，如果 pre 和 next 均为0，那么说明当前位置可以放花，我们修改 flowerbed 的值
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int len = flowerbed.length, cnt = 0;
        for (int i = 0; i < len; i++) {
            //若当前位置
            if (flowerbed[i] == 0) {
                int pre = (i == 0) ? 0 : flowerbed[i - 1];
                int next = (i == len - 1) ? 0 : flowerbed[i + 1];
                if (pre != 1 && next != 1) {
                    cnt++;
                    flowerbed[i] = 1;
                }
            }
        }
        return cnt >= n;
    }

    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        if (flowerbed == null || n > flowerbed.length) {
            return false;
        }
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0) {
                return n <= 1;
            } else {
                return n == 0;
            }
        }
        int len = flowerbed.length, cnt = 0;
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 && i + 1 < len && flowerbed[i + 1] != 1) {
                    flowerbed[i] = 1;
                    cnt++;
                } else if (i == len - 1 && flowerbed[i - 1] != 1) {
                    cnt++;
                } else if (i > 0 && flowerbed[i - 1] != 1 && flowerbed[i + 1] != 1) {
                    flowerbed[i] = 1;
                    cnt++;
                }
            }
        }
        return cnt >= n;
    }

    public static void main(String[] args) {
        new E605_CanPlaceFlowers().canPlaceFlowers(new int[]{0}, 1);
    }
}
