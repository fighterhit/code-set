package structure.StackAndQueue;

import structure.Array.GenericArray;

public class ArrayStack<E> implements MyStack<E> {

    //复用自定义泛型数组类
    GenericArray<E> array;

    public ArrayStack(int capacity) {
        this.array = new GenericArray<>(capacity);
    }

    public ArrayStack() {
        this.array = new GenericArray<>();
    }

    @Override
    public void push(E e) {
        try {
            array.addLast(e);
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public E pop() {
        try {
            return array.removeLast();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E peek() {
        try {
            return array.getLast();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            try {
                res.append(array.get(i));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }


}

