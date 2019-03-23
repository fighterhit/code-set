package sword.ch5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P214_StreamMedian {

    //最大堆：存较小部分
    PriorityQueue<Integer> l = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    //最小堆,java默认最小堆：存较大部分
    PriorityQueue<Integer> r = new PriorityQueue<>();
    int n = 0;

    public void Insert(Integer num) {
        if (n % 2 == 0) {
            l.add(num);
            r.add(l.poll());
        } else {
            r.add(num);
            l.add(r.poll());
        }
        n++;
    }

    public Double GetMedian() {
        if (n == 0) {
            return null;
        }
        if (n % 2 == 0) {
            return (l.peek() + r.peek()) / 2.0;
        }
        return (double) r.peek();
    }
}
