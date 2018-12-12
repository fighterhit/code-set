package leetcode.middle;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * <p>
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 *
 * @author Fighter.
 */
public class M61_RotateList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k < 1) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), tmpHead = head;
        dummyHead.next = head;
        int len = getLen(head);
        int tmp = len - k % len;
        if (tmp == len) {
            return head;
        }
        head = dummyHead;
        for (int i = 0; i < tmp; i++) {
            head = head.next;
        }
        ListNode newHead = head.next, newTail = head;
        while (head.next != null) {
            head = head.next;
        }
        head.next = dummyHead.next;
        newTail.next = null;
        return newHead;
    }

    int getLen(ListNode head) {
        int len = 0;
        //求链表长度
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    //将链表构成环
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k < 1) {
            return head;
        }
        ListNode newHead = head, tail = head;
        //求链表长度
        int len = 1;
        //注意判断条件，tail最终指向尾结点（而不是null），为构造环提供条件
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        //构成环
        tail.next = head;
        k %= len;
        if (k != 0) {
            for (int i = 0; i < len - k; i++) {
                tail = tail.next;
            }
        }
        newHead = tail.next;
        tail.next = null;
        return newHead;
    }
}