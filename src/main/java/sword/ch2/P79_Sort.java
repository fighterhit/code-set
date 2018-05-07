package sword.ch2;

/**
 * @author Fighter Created on 2018/4/30.
 */
public class P79_Sort {

}

class QuickSort {

    public static void sort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        quickSort(data, 0, data.length - 1);
    }

    //快排
    public static void quickSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotPos = partition(data, left, right);
        quickSort(data, left, pivotPos - 1);
        quickSort(data, pivotPos + 1, right);

    }

    private static int partition(int[] data, int left, int right) {
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