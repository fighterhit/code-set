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
        if (input.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        int left = 0, right = input.length - 1;
        return GetLeastNumbers_Solution(input, k, left, right);
    }

    //todo
    private ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k, int left, int right) {
        return null;
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
}
