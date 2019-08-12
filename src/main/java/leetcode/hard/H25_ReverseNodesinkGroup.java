package leetcode.hard;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * @author Fighter.
 */
public class H25_ReverseNodesinkGroup {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, tail = pre, cur = dummyHead;
        dummyHead.next = head;
        int i = 0;
        while (true) {
            //cur 每次遍历到每组的第 k 个节点
            for (i = 0; i < k && cur != null; i++) {
                cur = cur.next;
            }
            //若节点数不够 k 个或 cur 为null
            if (i != k || cur == null) {
                break;
            }
            //保存下一段链表头节点
            tail = cur.next;
            cur.next = null;
            //翻转
            pre.next = reverse(head);
            //head 记录原始头节点，翻转后修改 next 指向下一段链表头节点
            head.next = tail;

            pre = head;
            head = tail;
            cur = pre;
        }
        return dummyHead.next;
    }

    ListNode reverse(ListNode head) {
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
