package leetcode.middle;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * Example 1:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * <p>
 * Example 2:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 * 每行从左向右递增，并且本行末尾元素小于下一行首个元素
 */
public class M74_Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0]) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        //二分先查在第几行，注意这里初始化 r = m，即右边界可为数组最右边界，满足用例 [[1]], 2，否则会返回l = 0
        int l = 0, r = m, mid = -1;
        while (l < r) {
            mid = l + (r - l >> 1);
            if (target <= matrix[mid][0]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l < m && matrix[l][0] == target) {
            return true;
        }
        int row = l - 1;
        l = 0;
        r = n - 1;
        while (l <= r) {
            mid = l + (r - l >> 1);
            if (target == matrix[row][mid]) {
                return true;
            } else if (target < matrix[row][mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * 也可以使用一次二分查找法，如果按S型遍历该二维数组，可以得到一个有序的一维数组，那么我们只需要用一次二分查找法，而关键就在于坐标的转换
     * 如何把二维坐标和一维坐标转换是关键点，把一个长度为n的一维数组转化为m*n的二维数组(m*n = n)后，那么原一维数组中下标为i的元素将出现在二维数组中的[i/n][i%n]的位置
     */

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0]) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1, mid = -1;
        while (l <= r) {
            mid = l + (r - l >> 1);
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (target < matrix[mid / n][mid % n]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    public static int binarySearch2(int[] matrix, int target) {
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid] == target) return mid;
            else if (matrix[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }

    public static void main(String[] args) {
//        new M74_Searcha2DMatrix().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3);
//        new M74_Searcha2DMatrix().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 11);
        int m = binarySearch2(new int[]{1, 3, 4}, 5);
        System.out.println(m);
    }
}
