package leetcode.easy;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 * <p>
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 * <p>
 * 参考
 * E26_RemoveDuplicatesfromSortedArray 原地移动元素
 * M82_RemoveDuplicatesfromSortedListII 不保留重复元素
 */
public class E83_RemoveDuplicatesfromSortedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode i = head;
        for (ListNode j = head; j != null; j = j.next) {
            if (j.val != i.val) {
                i.next = j;
                i = i.next;
            }
        }
        i.next = null;
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    //递归版
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates3(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
