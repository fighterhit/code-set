package algorithm.LinkList;

/**
 * 找链表中点
 *
 * @author Fighter.
 */
public class FindMid {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //1,2,3 -> 2    1,2,3,4 -> 2
    public static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head;
        /**
         * 偶数节点：1->2->3->4
         * fast.next.next == null, slow = 2
         * 奇数节点：1->2->3->4->5
         * fast.next == null, slow = 3
         */
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //1,2,3 -> 2  1,2,3,4 -> 3
    public static ListNode findMid2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head;
        /**
         * 偶数节点：1->2->3->4
         * fast == null, slow = 3
         * 奇数节点：1->2->3->4->5
         * fast.next == null, slow = 3
         */
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int n = arr.length;
        ListNode head = createLinkedList(arr, n);
        ListNode mid = findMid(head);
        System.out.println(mid.val);
        ListNode mid2 = findMid2(head);
        System.out.println(mid2.val);
    }
}
