package sword.ch5;

import structure.SetMap.Map.Map;

/**
 * @author Fighter.
 */
public class P205_MoreThanHalfNumber {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null) {
            return 0;
        }
        int result = array[0], cnt = 1;
        for (int i = 1; i < array.length; i++) {
            if (cnt == 0) {
                result = array[i];
                cnt = 1;
            } else if (result == array[i]) {
                cnt++;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int n : array) {
            if (n == result) {
                cnt++;
            }
        }
        if (cnt * 2 > array.length) {
            return result;
        }
        return 0;
    }

    public int MoreThanHalfNum_Solution2(int[] array) {
        if (array == null) {
            return 0;
        }
        int l = 0, r = array.length - 1, mid = array.length >> 1;
        int p = partition(array, l, r);
        while (p != mid) {
            if (p > mid) {
                r = p - 1;
                p = partition(array, l, r);
            } else {
                l = p + 1;
                p = partition(array, l, r);
            }
        }
        int result = array[p];
        int cnt = 0;
        for (int n : array) {
            if (n == result) {
                cnt++;
            }
        }
        if (cnt * 2 > array.length) {
            return result;
        }
        return 0;
    }

    private int partition(int[] data, int left, int right) {
        int pivot = data[left];
        while (left < right) {
            while (left < right && pivot <= data[right]) {
                right--;
            }
            //把小的移动到左边
            data[left] = data[right];
            while (left < right && data[left] <= pivot) {
                left++;
            }
            //把大的移动到右边
            data[right] = data[left];
        }
        //最后把pivot赋值到中间，可画图，因为是right先从右边先移动，最后left向左移与right重合，故将pivot赋给data[left]
        data[left] = pivot;
        return left;
    }
}