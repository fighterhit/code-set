package sword.ch6;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * H239_SlidingWindowMaximum 求滑动窗口和的最大值列表
 */
public class P288_MaxInSlidingWindow {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length < size || size <= 0) {
            return res;
        }
        //大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //初始化窗口，加入前三个元素
        for (int i = 0; i < size; i++) {
            heap.add(num[i]);
        }
        res.add(heap.peek());
        //i, j 窗口首尾指针，窗口开始移动
        //注意，for循环每次先删除上一窗口中最左端元素，再加新元素，然后求最大值，即初始从第二个窗口求最大值开始
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            heap.remove(num[i]);
            heap.add(num[j]);
            res.add(heap.peek());
        }
        return res;
    }
}
