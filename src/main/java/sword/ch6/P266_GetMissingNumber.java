package sword.ch6;

public class P266_GetMissingNumber {

    static int getMissingNumber(int[] data) {
        if (data == null || data.length <= 0) {
            return -1;
        }
        int l = 0, r = data.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (data[m] == m + 1) {
                return m;
            } else if (data[m] == m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        //注意这里需要判断是否到达右边界
        if (l == data.length) {
            return data.length;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{0, 1, 3, 4, 5, 6}; //6
//        int[] data1 = new int[]{0, 1, 2, 3, 4, 5}; //6
        int[] data2 = new int[]{0, 1, 3, 4, 5}; //2
        int[] data3 = new int[]{1, 2}; //0
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));
        System.out.println(getMissingNumber(data3));
    }
}
