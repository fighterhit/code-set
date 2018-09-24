package structure.StackAndQueue;

import structure.Array.GenericArray;

public class ArrayQueue<E> implements MyQueue<E> {

    private GenericArray<E> array;

    public ArrayQueue(int capacity) {
        this.array = new GenericArray<>(capacity);
    }

    public ArrayQueue() {
        this.array = new GenericArray<>();
    }


    @Override
    public void enqueue(E e) {
        try {
            array.addLast(e);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public E dequeue() {
        try {
            return array.removeFirst();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E getFront() {
        try {
            return array.getFirst();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < getSize(); i++) {
            try {
                res.append(array.get(i));
                if (i != getSize() - 1){
                    res.append(", ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
