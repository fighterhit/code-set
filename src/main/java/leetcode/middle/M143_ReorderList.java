package leetcode.middle;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * <p>
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * @author Fighter.
 * <p>
 * 1. 使用快慢指针来找到链表的中点，并将链表从中点处断开，形成两个独立的链表。
 * 2. 将第二个链翻转。
 * 3. 将第二个链表的元素间隔地插入第一个链表中。
 */
public class M143_ReorderList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        //找中点
        ListNode mid = findMid(head);
        ListNode l1 = head, l2 = mid.next;
        mid.next = null;
        //翻转第2个链表
        l2 = reverse(l2);
        //合并两个链表
        while (l2 != null) {
            //暂存 l1 后继
            ListNode next = l1.next;
            //注意改变指针的先后顺序！
            l1.next = l2;
            l2 = l2.next;
            l1.next.next = next;
            l1 = next;
        }
    }

    //求链表中点
    private static ListNode findMid(ListNode head) {
        ListNode fast = head, slow = head;
        //快慢指针，每次快指针比慢指针多走一步，当快指针走到最后一个节点（不是null）时，慢指针比快指针少走一半，即在链表中间
        //注意这里的条件，节点总数为偶数时，这里得到的是第 count/2-1 个结点，若换成 fast!= null && fast.next!= null，则得到的是第 count/2 个节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //翻转链表：
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    static ListNode createLinkedList(int[] arr, int n) {
        if (n == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode curNode = head;
        for (int i = 1; i < n; i++) {
            curNode.next = new ListNode(arr[i]);
            curNode = curNode.next;
        }
        return head;
    }

    static void printLinkedList(ListNode head) {
        ListNode curNode = head;
        while (curNode != null) {
            System.out.print(curNode.val + " ->");
            curNode = curNode.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int n = arr.length;
        ListNode head = createLinkedList(arr, n);
        printLinkedList(head);
        new M143_ReorderList().reorderList(head);
    }
}
