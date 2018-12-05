package leetcode.middle;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * <p>
 * m,n超过范围？ m > n?
 */
public class M92_ReverseLinkedListII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode tail = reverse(head, n - m);

    }

    //返回尾节点tail
    ListNode reverse(ListNode head, int k) {
        ListNode pre, cur = head, next;

    }
}
