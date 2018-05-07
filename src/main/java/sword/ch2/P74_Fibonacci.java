package sword.ch2;

/**
 * @author Fighter Created on 2018/4/29.
 */
public class P74_Fibonacci {
    //递归
    public int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    //动态规划，空间复杂度为O(n)
    public int Fibonacci2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    //迭代 空间复杂度降到O(1)

    public int Fibonacci3(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, c = -1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

}
