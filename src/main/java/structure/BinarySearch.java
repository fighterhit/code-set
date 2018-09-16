package structure;

/**
 * @author Fighter Created on 2018/6/29.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int key = 9;
        int[] arr = new int[]{2, 3, 5, 6, 4, 7, 9, 7};
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 2);
            if (arr[mid] == key) {
                System.out.println(mid);
                return;
            } else if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("No");
    }
}
