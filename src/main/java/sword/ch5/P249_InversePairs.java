package sword.ch5;

import java.util.Arrays;

public class P249_InversePairs {
    long count;
    int[] tmp;

    public int InversePairs(int[] array) {
        tmp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }

    private void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    private void merge(int[] array, int l, int mid, int r) {
        int i = l, j = mid + 1, k = l;
        //注意条件是 ||
        while (i <= mid || j <= r) {
            if (i > mid) {
                tmp[k++] = array[j++];
            } else if (j > r) {
                tmp[k++] = array[i++];
            } else if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
                //array[i...mid] 都比 array[j] 大
                count += mid - i + 1;
            }
        }

        //注意辅助数组要覆盖原数组
        for (int m = l; m <= r; m++) {
            array[m] = tmp[m];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 7, 8, 1, 2, 3, 4};
        System.out.println(new P249_InversePairs().InversePairs(arr));
        System.out.println(Arrays.toString(arr));
    }
}
