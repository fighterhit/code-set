package sword.ch3;

/**
 * @author Fighter.
 */
public class P145_MergeSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = new ListNode(-1), tmp = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if (list1 == null) {
            head.next = list2;
        } else {
            head.next = list1;
        }
        return tmp.next;
    }
}
