package sword.ch5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class P209_KLeastNumbers {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null) {
            return null;
        }
        //注意判断条件
        if (k > input.length || k <= 0) {
            return new ArrayList<>();
        }

        int left = 0, right = input.length - 1;
        int p = partition(input, left, right);
        ArrayList<Integer> res = new ArrayList<>();
        //必须是 k-1，因为 k 可能等于 input.length，此时若条件为 p != k，则 left = p + 1 越界
        //用例 int[] data1 = {6, 1, 3, 5, 4, 2};
        while (p != k - 1) {
            if (p < k - 1) {
                left = p + 1;
                p = partition(input, left, right);
            } else {
                right = p - 1;
                p = partition(input, left, right);
            }
        }
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    int partition(int[] data, int l, int r) {
        int pivot = data[l];
        while (l < r) {
            while (l < r && data[r] >= pivot) {
                r--;
            }
            data[l] = data[r];
            while (l < r && data[l] <= pivot) {
                l++;
            }
            data[r] = data[l];
        }
        data[l] = pivot;
        return l;
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
