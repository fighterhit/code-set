package sword.ch6;

public class P263_NumberOfK {

    public int GetNumberOfK2(int[] array, int k) {
        int l = binarySearch(array, k);
        int r = binarySearch(array, k + 1) - 1;
        if (l == array.length || array[l] != k) {
            return 0;
        }
        return r - l + 1;
    }

    int binarySearch(int[] nums, int key) {
        //注意 h 初始值！！！
        int l = 0, h = nums.length;
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

    int getFirstKIndex(int[] arr, int l, int r, int k) {
        if (l > r) {
            return -1;
        }
        int midIndex = l + (r - l) / 2;
        int midData = arr[midIndex];
        //如果数组中间值等于k，则判断是否是第一个k，是的话直接返回当前索引，否则将查找有边界 r 更新为 midIndex - 1
        if (midData == k) {
//            if ((midIndex > 0 && arr[midIndex - 1] != k) || midIndex == 0) {
            if (midIndex == 0 || arr[midIndex - 1] != k) {
                return midIndex;
            } else {
                r = midIndex - 1;
            }
        } else if (midData > k) {
            r = midIndex - 1;
        } else {
            l = midIndex + 1;
        }
        return getFirstKIndex(arr, l, r, k);
    }

    int getLastKIndex(int[] arr, int l, int r, int k) {
        if (l > r) {
            return -1;
        }
        int midIndex = l + (r - l) / 2;
        int midData = arr[midIndex];
        //如果数组中间值等于k，则判断是否是第一个k，是的话直接返回当前索引，否则将查找有边界 r 更新为 midIndex - 1
        if (midData == k) {
//            if ((midIndex > 0 && arr[midIndex - 1] != k) || midIndex == 0) {
            if (midIndex == arr.length - 1 || arr[midIndex + 1] != k) {
                return midIndex;
            } else {
                l = midIndex + 1;
            }
        } else if (midData > k) {
            r = midIndex - 1;
        } else {
            l = midIndex + 1;
        }
        return getLastKIndex(arr, l, r, k);
    }

    public int GetNumberOfK(int[] array, int k) {
        int lk = getFirstKIndex(array, 0, array.length - 1, k), rk = getLastKIndex(array, 0, array.length - 1, k);
        if (lk != -1 && rk != -1) {
            return rk - lk + 1;
        }
        return 0;
    }
}
