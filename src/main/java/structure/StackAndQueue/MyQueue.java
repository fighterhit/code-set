package structure.StackAndQueue;

public interface MyQueue<E> {
    void enqueue(E e);
    E dequeue();
    int getSize();
    boolean isEmpty();
    E getFront();
}
