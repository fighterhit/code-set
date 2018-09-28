package structure.Recursion;

/**
 * 递归：
 * 1. 求解最基本问题
 * 2. 把原问题转化成更小的问题
 * 3. 注意递归函数"宏观语意"，递归函数就是一个函数，完成一个功能
 * <p>
 * 求解 arr[0,1,2....n-1] 数组的和
 */
public class ArraySum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,2,3,4,5,6}));
    }
}
