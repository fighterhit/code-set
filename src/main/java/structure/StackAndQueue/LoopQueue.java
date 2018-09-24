package structure.StackAndQueue;

public class LoopQueue<E> implements MyQueue<E> {

    E[] data;
    int front, tail;
    int size;

    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this.data = (E[]) new Object[10];
    }


    @Override
    public void enqueue(E e) {
        //先判断队列是否满，满则扩容两倍
        if ((tail + 1) % data.length == front) {
            reSize(getCapacity() * 2);
        }
        data[tail] = e;
        size++;
        //每次添加完新元素后改变尾指针
        tail = (tail + 1) % data.length;
    }

    private void reSize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        //注意改变首尾指针为新数组首尾
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0){
            reSize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
//        return size == 0;
        return front == tail;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    private int getCapacity() {
        return data.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if (i + 1 != tail){
                res.append(", ");
            }
        }
        res.append("]");
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
