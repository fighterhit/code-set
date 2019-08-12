package leetcode.middle;

/**
 * Algorithm of Insertion Sort:
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 * 链表的插入排序
 */
public class M147_InsertionSortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //dummy 后面是新构建的排序链表，每次从前往后遍历插入新元素，与数组插入排序从后往前找插入不同
        ListNode dummy = new ListNode(-1), pre = dummy, cur = head;
        while (cur != null) {
            pre = dummy;
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //将 cur 插到 pre 和 pre.next 之间
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
