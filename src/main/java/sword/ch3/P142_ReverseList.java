package sword.ch3;

import sword.ListNode;

/**
 * @author Fighter.
 */
public class P142_ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
