package leetcode.middle;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * <p>
 * P187_CopyComplexList
 */
public class M138_CopyListwithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node copy;

        //复制next
        while (cur != null) {
            Node next = cur.next;

            copy = new Node();
            copy.val = cur.val;
            copy.next = next;

            cur.next = copy;
            cur = cur.next.next;
        }

        cur = head;
        //复制random
        while (cur != null) {
            if (cur.random != null) {
                copy = cur.next;
                Node random = cur.random;
                copy.random = random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        Node dummyHead = new Node(), copyIter = dummyHead;
        //分离copy链表
        while (cur != null) {
            Node next = cur.next.next;

            //获得复制节点
            copy = cur.next;
            //复制节点迭代
            copyIter.next = copy;
            copyIter = copy;

            //注意需要恢复原始链表！！！
            cur.next = next;
            cur = next;
        }
        return dummyHead.next;
    }
}
