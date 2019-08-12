package sword.ch5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * <p>
 * 参考 M215_KthLargestElementinanArray
 */
public class P209_KLeastNumbers {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || k > input.length || k <= 0) {
            return res;
        }
        int l = 0, r = input.length - 1, p;
        while (true) {
            p = partition(input, l, r);
            if (p < k - 1) {
                l = p + 1;
            } else if (p > k - 1) {
                r = p - 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    int partition(int[] data, int l, int r) {
        int pivot = data[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && data[i] < pivot) {
                i++;
            }
            while (j >= l + 1 && data[j] > pivot) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(data, i, j);
            i++;
            j--;
        }
        swap(data, l, j);
        return j;
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    //最大堆：O(NlogK) + O(K)
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        if (input == null) {
            return null;
        }

        //k不能大于输入长度
        if (k > input.length || k <= 0) {
            return new ArrayList<>();
        }

        //默认最小堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((integer, t1) -> t1 - integer);

        for (int i = 0; i < input.length; i++) {
            maxHeap.add(input[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

    public static void main(String[] args) {
        int[] data1 = {6, 1, 3, 5, 4, 2};
        System.out.println(new P209_KLeastNumbers().GetLeastNumbers_Solution(data1, 6));
        int[] data2 = {6, 1, 3, 5, 4, 2};
        System.out.println(new P209_KLeastNumbers().GetLeastNumbers_Solution2(data1, 5));
        int[] data3 = {6, 1, 3, 5, 4, 2};
        System.out.println(new P209_KLeastNumbers().GetLeastNumbers_Solution2(data1, 5));

    }
}
