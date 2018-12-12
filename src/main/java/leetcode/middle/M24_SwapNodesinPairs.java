package leetcode.middle;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Note:
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * @author Fighter.
 */
public class M24_SwapNodesinPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, curNode = head, next = curNode.next;
        pre.next = curNode;
        while (curNode != null && next != null) {
            //翻转
            pre.next = next;
            curNode.next = next.next;
            next.next = curNode;
            //迭代
            pre = curNode;
            curNode = curNode.next;
            if (curNode != null) {
                next = curNode.next;
            } else {
                break;
            }
        }
        return dummyHead.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, node1, node2;
        dummyHead.next = head;
        while (pre.next != null && pre.next.next != null) {
            node1 = pre.next;
            node2 = pre.next.next;
            //翻转
            pre.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            //迭代
            pre = node1;
        }
        return dummyHead.next;
    }
}
