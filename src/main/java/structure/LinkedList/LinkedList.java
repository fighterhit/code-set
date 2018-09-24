package structure.LinkedList;

import structure.StackAndQueue.MyQueue;

/**
 * 无头节点列表
 *
 * @param <E>
 */
public class LinkedList<E> {

    private class Node {
        private E e;
        private Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 头节点始终指向链表头新插节点
     */
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 链表头部插入节点，每次将新结点指向老的head，并将新的结点头节点置为当前节点
     *
     * @param e
     */
    public void addFirst(E e) {
        head = new Node(e, head);
        size++;
    }

    /**
     * 注意是在末尾添加元素，因此 index 为 size 而不是 size - 1
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            //当无虚拟节点时，head 每次指向当前节点，此处边界条件为 index - 1
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
          /*
            Node newNode = new Node(e);
            newNode.next = prev.next;
            prev.next = newNode;
          */
            //上面等价于
            prev.next = new Node(e, prev.next);
            //容量+1
            size++;
        }

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
//        return size == 0;
        return head == null;
    }

    public E getFront() {
        return head.e;
    }
}
