package sword.ch3;

import sword.ListNode;

/**
 * @author Fighter.
 */
public class P134_KthNodeFromEnd {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p = head, q = head;
        for (int i = 0; i < k; i++) {
            if (p != null) {
                p = p.next;
            } else {
                return null;
            }
        }

        while (p!=null){
            p = p.next;
            q = q.next;
        }
        return q;
    }
}
