package sword.ch5;

import java.util.Arrays;

/**
 * 一个丑数的因子只有2,3,5，那么丑数p = 2 ^ x * 3 ^ y * 5 ^ z，换句话说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，那么我们从1开始乘以2,3,5，就得到2,3,5三个丑数，在从这三个丑数出发乘以2,3,5就得到4，6,10,6，9,15,10,15,25九个丑数，我们发现这种方法会得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。
 * 链接：https://www.nowcoder.com/questionTerminal/6aa9e04fc3794f68acf8778237ba065b
 * 来源：牛客网
 * 记录三个指针显示到达哪一步；“|”表示指针,arr表示丑数数组；
 * （1）1
 * |2
 * |3
 * |5
 * 目前指针指向0,0,0，队列头arr[0] * 2 = 2,  arr[0] * 3 = 3,  arr[0] * 5 = 5
 * （2）1 2
 * 2 |4
 * |3 6
 * |5 10
 * 目前指针指向1,0,0，队列头arr[1] * 2 = 4,  arr[0] * 3 = 3, arr[0] * 5 = 5
 * （3）1 2 3
 * 2| 4 6
 * 3 |6 9
 * |5 10 15
 * 目前指针指向1,1,0，队列头arr[1] * 2 = 4,  arr[1] * 3 = 6, arr[0] * 5 = 5
 */
public class P240_GetUglyNumber {

    //根据定义 暴力法
    boolean isUgly(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }

    public int GetUglyNumber_Solution(int index) {
        if (index < 7) {
            return index;
        }
        int[] arr = new int[index];
        arr[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        //注意是 i = 1 开始！
        for (int i = 1; i < index; i++) {
            int next2 = arr[p2] * 2, next3 = arr[p3] * 3, next5 = arr[p5] * 5;
            arr[i] = Math.min(next2, Math.min(next3, next5));
            if (arr[i] == next2) {
                p2++;
            }
            if (arr[i] == next3) {
                p3++;
            }
            if (arr[i] == next5) {
                p5++;
            }
        }
        return arr[index - 1];
    }
}
