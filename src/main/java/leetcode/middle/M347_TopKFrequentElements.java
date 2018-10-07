package leetcode.middle;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * @author Fighter Created on 2018/10/5.
 */
public class M347_TopKFrequentElements {

   /*
    //使用自定义类
    class Freq implements Comparable<Freq> {
        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) throws IllegalAccessException {
        TreeMap<Integer, Integer> next = new TreeMap<>();
        for (int num : nums) {
            if (next.containsKey(num)) {
                next.put(num, next.get(num) + 1);
            } else {
                next.put(num, 1);
            }
        }
        MyPriorityQueue<Freq> myPriorityQueue = new MyPriorityQueue<>();
        for (Integer key : next.keySet()) {
            if (myPriorityQueue.getSize() < k) {
                myPriorityQueue.enqueue(new Freq(key, next.get(key)));
            } else if (next.get(key) > myPriorityQueue.getFront().freq) {
                myPriorityQueue.dequeue();
                myPriorityQueue.enqueue(new Freq(key, next.get(key)));
            }
        }
        List<Integer> res = new LinkedList<>();
        while (!myPriorityQueue.isEmpty()) {
            res.add(myPriorityQueue.dequeue().e);
        }
        return res;
    }
    */

    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //jdk默认是最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }
        List<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.remove());
        }
        return res;
    }


    private static void printList(List<Integer> nums) {
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IllegalAccessException {

        int[] nums = {1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3};
        int k = 2;
        printList(((new M347_TopKFrequentElements()).topKFrequent(nums, k)));
    }
}
