package sword.ch5;

/**
 * https://blog.csdn.net/xudli/article/details/46798619
 * intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位). 
 * 例子:
 * 以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况:
 *     case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
 *     case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次.
 *     case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.   (1xx~199 算第 1 个 1000 内的 100 个百位为 1，11xx~1199 算 2000 内第 2 个 1000 内的 100 )
 * 以上三种情况可以用 一个公式概括:
 * (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
 *
 * https://www.cnblogs.com/grandyang/p/4629032.html
 * 参考 H233_NumberofDigitOne
 */
public class P221_NumberOf1 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        //注意 a, b, i 为long类型，否则 leetcode 233. Number of Digit One 通不过
        long a, b;
        for (long i = 1; i <= n; i *= 10) {
            a = n / i;
            b = n % i;
            // (a + 8) / 10 * i 表示 case1、case3
            // a % 10 == 1 ? b + 1 : 0 表示 case2
            cnt += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    //牛客能过，leetcode 超时
    public int NumberOf1Between1AndN_Solution2(int n) {
        int cnt = 0;
        while (n != 0) {
            String s = String.valueOf(n);
            char[] sarr = s.toCharArray();
            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] == '1') {
                    cnt++;
                }
            }
            n--;
        }
        return cnt;
    }

    public int NumberOf1Between1AndN_Solution3(int n) {
        int ones = 0;
        for (long i = 1; i <= n; i *= 10) {
            long a = n / i, b = n % i;
            ones += (a + 8) / 10 * i;
            if (a % 10 == 1) {
                ones += b + 1;
            }
        }
        return ones;
    }
}