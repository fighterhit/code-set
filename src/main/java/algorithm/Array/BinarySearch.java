package algorithm.Array;

/**
 * @author Fighter Created on 2018/6/29.
 */
public class BinarySearch {
    public static int binarySearch(Comparable[] arr, int n, Comparable target) {
        // 在[l...r]的范围里寻找target
        int l = 0, r = n - 1;
        // 当 l == r时,区间[l...r]依然是有效的
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            // target在[mid+1...r]中; [l...mid]一定没有target
            else if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;
            }
            // target在[l...mid-1]中; [mid...r]一定没有target
            else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 正常实现：
     * 循环退出时如果仍然没有查找到 key，那么表示查找失败。可以有两种返回值：
     * -1：以一个错误码表示没有查找到 key
     * l：将 key 插入到 nums 中的正确位置
     */
    public static int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l >> 1);
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    /**
     * 变种：
     * 该实现和正常实现有以下不同：
     * 循环条件为 l < h
     * h 的赋值表达式为 h = m
     * 最后返回 l 而不是 -1
     * <p>
     * 在 nums[m] >= key 的情况下，可以推导出最左 key 位于 [l, m] 区间中，这是一个闭区间。h 的赋值表达式为 h = m，因为 m 位置也可能是解。
     * <p>
     * 在 h 的赋值表达式为 h = mid 的情况下，如果循环条件为 l <= h，那么会出现循环无法退出的情况，因此循环条件只能是 l < h。以下演示了循环条件为 l <= h 时循环无法退出的情况：
     * <p>
     * nums = {0, 1, 2}, key = 1
     * l   m   h
     * 0   1   2  nums[m] >= key
     * 0   0   1  nums[m] < key
     * 1   1   1  nums[m] >= key
     * 1   1   1  nums[m] >= key
     * ...
     * 当循环体退出时，不表示没有查找到 key，因此最后返回的结果不应该为 -1。为了验证有没有查找到，需要在调用端判断一下返回位置上的值和 key 是否相等。
     */
    public static int binarySearch2(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l >> 1);
            if (nums[m] >= key) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        int n = (int) Math.pow(10, 7);
        Integer data[] = Util.generateOrderedArray(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++)
            if (i != binarySearch(data, n, i))
                throw new IllegalStateException("find i failed!");
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete.");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");
    }
}
