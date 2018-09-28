package leetcode.middle;

import sword.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 *
 * @author Fighter Created on 2018/5/9.
 */
public class M82_RemoveDuplicatesfromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preNode = null;
        ListNode pNode = head;
        ListNode next = null;
        boolean needDel = false;

        while (pNode != null) {

            next = pNode.next;
            if (next != null && next.val == pNode.val) {
                needDel = true;
            }

            if (!needDel) {
                preNode = pNode;
                pNode = pNode.next;
            } else {

                int val = (Integer) pNode.val;
                ListNode toBeDelNode = pNode;

                while (toBeDelNode != null && (Integer) toBeDelNode.val == val) {
                    next = toBeDelNode.next;
                    toBeDelNode = next;
                }

                if (preNode == null) {
                    head = next;
                } else {
                    preNode.next = next;
                }
                needDel = false;
                pNode = next;
            }

        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;





        return dummy.next;
    }
}
