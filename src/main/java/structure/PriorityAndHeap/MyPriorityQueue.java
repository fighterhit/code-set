package structure.PriorityAndHeap;

import structure.StackAndQueue.MyQueue;

/**
 * @author Fighter Created on 2018/10/5.
 */
public class MyPriorityQueue<E extends Comparable> implements MyQueue<E> {

    private MaxHeap<E> maxHeap = new MaxHeap<>();

    @Override
    public void enqueue(E e) {
        try {
            maxHeap.add(e);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public E dequeue() {
        try {
            return maxHeap.extractMax();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        try {
            return maxHeap.findMax();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
