package leetcode.middle;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * <p>
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * @author Fighter.
 */
public class M215_KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        //默认最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(num);
            } else {
                if (num > priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.add(num);
                }
            }
        }
        return priorityQueue.peek();
    }
}
