package structure.LinkedList;

import structure.StackAndQueue.MyQueue;

/**
 * 带尾节点的链表实现的队列
 *
 * @param <E>
 */
public class LinkedListQueueWithTail<E> implements MyQueue<E> {

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

    //head 指向第一个元素节点，tail 始终指向最后一个元素节点
    private Node head, tail;
    private int size;

    @Override
    public void enqueue(E e) {
        if (head == null) {
            head = tail = new Node(e);
        } else {
            /*Node last = tail;
            tail = new Node(e);
            last.next = tail;*/
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node ret = head;
        head = head.next;
        size--;
        return ret.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        if (!isEmpty()) {
            return head.e;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node cur = head;
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                res.append(cur + " -> ");
            } else {
                res.append(cur + " end");
            }
            cur = cur.next;
        }
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListQueueWithTail<Integer> queue = new LinkedListQueueWithTail<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

/**
 * 只有头节点实现的队列，效率低
 *
 * @param <E>
 */
class LinkedListQueueWithoutTail<E> implements MyQueue<E> {

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

    DummyHeadLinkedList<E> linkedList;

    public LinkedListQueueWithoutTail() {
        this.linkedList = new DummyHeadLinkedList<>();
    }

    @Override
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public E getFront() {
        return linkedList.getFirst();
    }
}
