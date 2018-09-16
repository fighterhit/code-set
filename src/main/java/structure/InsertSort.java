package structure;

import java.util.Arrays;

/**
 * @author Fighter Created on 2018/6/29.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 6, 4, 7, 9, 7};
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        System.out.println(Arrays.toString(arr));
    }
}
