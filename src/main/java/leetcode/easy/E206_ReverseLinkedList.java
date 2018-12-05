package leetcode.easy;

/**
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class E206_ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //iteratively
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            //当前节点不为空时，再获取当前节点后继节点
            next = cur.next;
            //反转
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        //注意，退出循环时cur一定为空指针
        return pre;
    }

    //recursively
}