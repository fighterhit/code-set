package sword.ch6;

/**
 * E268. Missing Number
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * https://leetcode.com/problems/missing-number/
 */
public class P266_GetMissingNumber {

    static int getMissingNumber(int[] data) {
        if (data == null || data.length <= 0) {
            return -1;
        }
        int l = 0, r = data.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (data[m] != m) {
                if (m == 0 || data[m - 1] == m - 1) {
                    return m;
                }
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        //注意这里需要判断是否到达右边界
        if (l == data.length) {
            return data.length;
        }
        return -1;
    }

    static int getMissingNumber2(int[] data) {
        if (data == null || data.length <= 0) {
            return -1;
        }
        int l = 0, r = data.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (data[m] == m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    /**
     * E268. Missing Number
     */
    static int getMissingNumber3(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int res = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            res = res ^ nums[i] ^ i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{0, 1, 3, 4, 5, 6, 7, 8, 9, 10}; //2
        int[] data2 = new int[]{0, 1, 3, 4, 5}; //2
        int[] data3 = new int[]{1, 2}; //0
        int[] data4 = new int[]{0, 1, 2, 3, 4}; //5
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));
        System.out.println(getMissingNumber(data3));
        System.out.println(getMissingNumber(data4));
    }
}
