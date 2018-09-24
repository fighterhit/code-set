package structure.StackAndQueue;

public interface MyStack<E> {
    void push(E e);
    E pop();
    boolean isEmpty();
    E peek();
    int getSize();

}
