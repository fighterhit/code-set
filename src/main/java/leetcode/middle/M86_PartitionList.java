package leetcode.middle;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * <p>
 * 三路快排变形!!!
 * 由于从左到右遍历了原有链表，故两个链表中元素的相对顺序不会发生变化。另外值得注意的是，在图中我们完好地保留了原有链表。
 * 事实上，在算法实现中，我们将节点从原有链表中移除，并将它们添加到别的链表中。没有使用任何额外的空间，只是将原有的链表元素进行移动。
 * https://leetcode-cn.com/problems/two-sum/solution/fen-ge-lian-biao-by-leetcode/
 */
public class M86_PartitionList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode less = new ListNode(0), lessHead = less, greater = new ListNode(0), greaterHead = greater;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        greater.next = null;
        less.next = greaterHead.next;
        return lessHead.next;
    }

    //分割成三部分，小于部分 small, 等于部分 equal, 大于部分 big
    public ListNode partition2(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        //没有用虚拟头节点（哑结点）dummyHead
        ListNode sH = null, sT = null;
        ListNode eH = null, eT = null;
        ListNode bH = null, bT = null;
        while (head != null) {
            ListNode next = head.next;
            if (head.val < x) {
                if (sH == null) {
                    sH = sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.val == x) {
                if (eH == null) {
                    eH = eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (bH == null) {
                    bH = bT = head;
                } else {
                    bT.next = head;
                    bT = bT.next;
                }
            }
            head = head.next;
        }
        if (sH != null) {
            sT.next = eH;
            eT = eT == null ? sT : eH;
        }
        if (eH != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }

}
