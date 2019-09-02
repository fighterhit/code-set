package leetcode.middle;

import java.util.*;

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
 * <p>
 * 用于求解 TopK Elements 问题，也就是 K 个最小元素的问题。可以维护一个大小为 K 的最小堆，最小堆中的元素就是最小元素。最小堆需要使用大顶堆来实现，大顶堆表示堆顶元素是堆中最大元素。这是因为我们要得到 k 个最小的元素，因此当遍历到一个新的元素时，需要知道这个新元素是否比堆中最大的元素更小，更小的话就把堆中最大元素去除，并将新元素添加到堆中。所以我们需要很容易得到最大元素并移除最大元素，大顶堆就能很好满足这个要求。
 * 堆也可以用于求解 Kth Element 问题，得到了大小为 k 的最小堆之后，因为使用了大顶堆来实现，因此堆顶元素就是第 k 大的元素。
 * 快速选择也可以求解 TopK Elements 问题，因为找到 Kth Element 之后，再遍历一次数组，所有小于等于 Kth Element 的元素都是 TopK Elements。
 * 可以看到，快速选择和堆排序都可以求解 Kth Element 和 TopK Elements 问题。
 */
public class M215_KthLargestElementinanArray {
    /**
     * 求 TopK 小 用最大堆，求 TopK 大 用最小堆
     * 堆 ：时间复杂度 O(NlogK)，空间复杂度 O(K)。
     */
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

    /**
     * 快速选择 ：时间复杂度 O(N)，空间复杂度 O(1)
     */
    public int findKthLargest2(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k - 1) {
                return nums[pivotIndex];
            } else if (pivotIndex < k - 1) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1, j = right;
        while (true) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            //注意，不是 i <= j
            while (left + 1 <= j && nums[j] < pivot) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(nums, i, j);
            //交换完注意i, j迭代
            i++;
            j--;
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
