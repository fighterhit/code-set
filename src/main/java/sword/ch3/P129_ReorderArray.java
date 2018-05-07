package sword.ch3;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author Fighter Created on 2018/5/6.
 */
public class P129_ReorderArray {

    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        //空间换时间
        int[] copy = array.clone();
        int oddCnt = 0;

        for (int i : array) {
            if ((i & 1) == 1) {
                oddCnt++;
            }
        }

        int i = 0, j = oddCnt;
        for (int num : copy) {
            if ((num & 1) == 1) {
                array[i++] = num;
            } else {
                array[j++] = num;
            }
        }
    }

    public static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 7, 7};
        reOrderArray(data);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print('\t');
        }
        System.out.println();
    }

}
