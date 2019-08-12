package sword.ch6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class P292_QueueWithMax {

    //可求最大值队列
    static class QueueWithMax<T extends Comparable> {

        private Deque<InternalData<T>> dataQueue;
        private Deque<InternalData<T>> maxQueue;
        int index;

        //自定义队列内部存储的数据结构
        class InternalData<T extends Comparable> {
            T val;
            int index;

            public InternalData(T val, int index) {
                this.val = val;
                this.index = index;
            }
        }

        public QueueWithMax() {
            this.dataQueue = new ArrayDeque<>();
            this.maxQueue = new ArrayDeque<>();
            this.index = 0;
        }

        public T max() {
            if (!maxQueue.isEmpty()) {
                return maxQueue.getFirst().val;
            }
            return null;
        }

        //添加元素时，从最大队列尾部比较，将小于插入元素值的元素全部删除再加入最大队列
        public void push(T val) {
            //最大队列非空 且 当前值比最大队列尾元素值大
            while (!maxQueue.isEmpty() && val.compareTo(maxQueue.getLast().val) >= 0) {
                maxQueue.removeLast();
            }
            InternalData<T> data = new InternalData<>(val, index);
            dataQueue.addLast(data);
            maxQueue.addLast(data);
            index++;
        }

        //删除元素时，从最大队列首部比较，通过比较 元素索引值 而不是 元素值大小 来确定最大队列首部元素是否就是当前元素即是否也需要删除
        public T pop() {
            if (dataQueue.isEmpty()) {
                return null;
            }
            InternalData<T> data = dataQueue.removeFirst();
            if (data.index == maxQueue.getFirst().index) {
                maxQueue.removeFirst();
            }
            return data.val;
        }
    }


    public static void main(String[] args) {
        QueueWithMax<Integer> queue = new QueueWithMax<>();
        queue.push(3);
        System.out.println(queue.max());
        queue.push(5);
        System.out.println(queue.max());
        queue.push(1);
        System.out.println(queue.max());
        System.out.println("开始出队后，调用max");
        System.out.println(queue.max());
        queue.pop();
        System.out.println(queue.max());
        queue.pop();
        System.out.println(queue.max());
        queue.pop();
        System.out.println(queue.max());
    }
}
