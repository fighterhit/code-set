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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, curNode = head, next = null;
        dummyHead.next = head;
        while (curNode != null) {
            next = curNode.next;
            //当前节点和后继节点值相等时，两指针一直向后移直到相邻两节点值不同
            while (next != null && next.val == curNode.val) {
                curNode = next;
                next = next.next;
            }
            //若前驱节点和当前节点相邻，则说明当前节点不是重复节点，将前驱和当前节点后移
            if (pre.next == curNode) {
                pre = curNode;
                curNode = next;
            } else {
                curNode = next;
                pre.next = next;
            }
        }
        return dummyHead.next;
    }
}
