package algorithm.DynamicProgramming;

/**
 * 整数划分（自身和全1都算）
 * 整数划分问题是将一个正整数n拆成一组数连加并等于n的形式，且这组数中的最大加数不大于n。
 * 如6的整数划分为
 * 6
 * 5 + 1
 * 4 + 2, 4 + 1 + 1
 * 3 + 3, 3 + 2 + 1, 3 + 1 + 1 + 1
 * 2 + 2 + 2, 2 + 2 + 1 + 1, 2 + 1 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1 + 1
 * <p>
 * 递归函数的声明为 int split(int n, int m);其中n为要划分的正整数，m是划分中的最大加数(当m > n时，最大加数为n)，
 * 1 当n = 1或m = 1时，split的值为1，可根据上例看出，只有一个划分1 或 1 + 1 + 1 + 1 + 1 + 1
 * 可用程序表示为if(n == 1 || m == 1) return 1;
 * <p>
 * 2 下面看一看m 和 n的关系。它们有三种关系
 * (1) m > n
 * 在整数划分中实际上最大加数不能大于n，因此在这种情况可以等价为split(n, n);
 * 可用程序表示为if(m > n) return split(n, n);
 * (2) m = n
 * 这种情况可用递归表示为split(n, m - 1) + 1，从以上例子中可以看出，就是最大加
 * 数为6和小于6的划分之和
 * 用程序表示为if(m == n) return (split(n, m - 1) + 1);
 * (3) m < n
 * 这是最一般的情况，在划分的大多数时都是这种情况。
 * 从上例可以看出，设m = 4，那split(6, 4)的值是最大加数小于4划分数和整数2的划分数的和。
 * 因此，split(n, m)可表示为split(n, m - 1) + split(n - m, m)
 * https://www.cnblogs.com/nokiaguy/archive/2008/05/11/1192308.html
 */
public class IntegerSplit {

    //获取整数划分数
    static int split(int n) {
        return split(n, n);
    }

    //待划分整数 n，最大加数
    private static int split(int n, int m) {
        if (n < 1 || m < 1) {
            return 0;
        }
        if (n == 1 || m == 1) {
            return 1;
        }
        //m 和 n的关系, 它们有三种关系: m > n,  m = n, m < n
        if (n < m) {
            return split(n, n);
        }
        if (n == m) {
            return 1 + split(n, m - 1);
        }
        // n > m
        return split(n - m, m) + split(n, m - 1);
    }

    public static void main(String[] args) {
        System.out.println(split(6));
    }
}

/**
 * 整数的分解方法（不带自身和全1）
 * 题目
 * 如下示例：
 * 1：共0种分解方法；
 * 2：共0种分解方法；
 * 3：3=2+1 共1种分解方法；
 * 4：4=3+1=2+1+1 共2种分解方法；
 * 5：5=4+1=3+2=3+1+1=2+2+1=2+1+1+1 共5种分解方法
 * 6：6=5+1=4+2=4+1+1=3+2+1=3+1+1+1=2+2+1+1=2+1+1+1+1 共7种分解方法
 * 以此类推，求一任意整数num有几种分解方法？
 * 输入一个数字（1到90），输出该整数的分解方法个数，如：
 * 输入：2——输出：0
 * 输入：3——输出：1
 * 输入：5——输出：5
 * <p>
 * 为保证输出的唯一性，保持降序排列:
 * 总结分解方法：
 * 1. 对于数num，按照分解情况的结尾数字考虑：以1结尾，以2结尾，...，以 Math.floor((num - 1) / 2) 结尾，每种结尾都先进行一次分解（以7为例，以1结尾时分解成6+1，以2结尾分解成5+2，以3结尾分解成4+3）
 * 2. 对于第一次分解出的两个数（num1，num2），进一步分解num1，且只在 num1 > 2*num2 时分解 num1（否则无法保证降序，例：5=3+2，3继续分解出2+1，则5=2+1+2不是降序）
 * 3. 若 num1 是偶数，计算分解情况时+1（例：5=4+1，进一步分解4时，要考虑4=2+2）
 * 4. 保证 num1 进一步分解的结尾的数>=num2（例：7=5+2，进一步分解5时，只能将5分解成3+2，若分解成任意以1结尾的情况，如 4+1，则 7=4+1+2 不是降序）
 * <p>
 * 因此，我们运用动态规划的方法，从3开始往大数分析，构造二维数组，横坐标表示当前分析的数，纵坐标表示当前分解情况结尾的数
 * <p>
 * https://www.cnblogs.com/xuehaoyue/p/6660315.html
 * https://sungd.github.io/2017/04/03/integer-decompose/#more?nsukey=9C6TrBvxdFPTW8IiYDwROwDAkWSlCcjuNKFPhAIv3f8juM6WrTxNw68k%2B%2Bj9j8I7M6XrYhkw0Pc%2Baa5%2FyuvvMczvCG9OcuMwMzL4qr3UKJdi0PGGiOUIHzVtED%2FFkkcYLtjH74CsMZD9N8drEsTPOcGtCBbzs74mHtQ3hN719VOeXUTOR5ezU3JAjFsTZ34c9jb5K7aOlgjAUm2yyhgi8g%3D%3D
 */
class IntegerSplit2 {
    public static void main(String[] args) {
        int[] test = {3, 4, 5, 6, 7};
        for (int num : test) {
            System.out.println(num + "\t(" + decompose(num) + ") 种");
        }
    }

    private static int decompose(int num) {
        if (num < 3) {
            return 0;
        }
        int[][] res = new int[num + 1][];
        for (int i = 3; i <= num; i++) {
            //1. 分解后最小数可能的种数, 对于数num，按照分解情况的结尾数字考虑：以1结尾，以2结尾，...，以Math.floor((num - 1) / 2)结尾
            int cols = (i - 1) / 2;
            res[i] = new int[cols + 1];
            //遍历所有结尾情况
            for (int j = 1; j <= cols; j++) {
                //对于最小为 j 的情况，至少有一个分解
                int cnt = 1;
                int bigger = i - j;
                //2. 只在num1 > 2*num2 时分解num1（否则无法保证降序)
                if (bigger > 2 * j) {
                    //3. 若大数为偶数，可分解为 bigger / 2 + bigger / 2。若num1是偶数，计算分解情况时+1（例：5=4+1，进一步分解4时，要考虑4=2+2）
                    if (bigger % 2 == 0) {
                        cnt++;
                    }
                    //4. 为保证降序，num1 进一步分解的结尾的数要 >= num2，所以从 j 开始
                    for (int k = j; k < res[bigger].length; k++) {
                        cnt += res[bigger][k];
                    }
                }
                res[i][j] = cnt;
            }
        }
        int total = 0;
        for (int i = 0; i < res[num].length; i++) {
            System.out.println(res[num].length);
            total += res[num][i];
        }
        return total;
    }
}