/**
 * @author Fighter Created on 2018/4/30.
 */
public class Solution {
    private static int[] fib = new int[40];

    static {
        fib[1] = 1;
        fib[2] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            System.out.println("i: "+i+" "+fib[i]);
        }
    }

    public static int Fibonacci(int n) {
        return fib[n];
    }

    public static void main(String[] args) {
        Fibonacci(4);
    }
}
