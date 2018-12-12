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
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //iteratively
    public ListNode reverseList(ListNode head) {
        //冗余判断，可要可不要
       /* if (head == null) {
            return head;
        }*/
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
    public ListNode reverseList2(ListNode head) {
        return reverseList2(head, null);
    }

    public ListNode reverseList2(ListNode head, ListNode pre) {
        if (head == null) {
            return pre;
        }
        ListNode next = head.next;
        head.next = pre;
        //继续处理剩下链表
        return reverseList2(next, head);
    }

    public ListNode reverseList3(ListNode head) {
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
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int n = arr.length;
        ListNode head = createLinkedList(arr, n);
        printLinkedList(head);

        ListNode newHead = new E206_ReverseLinkedList().reverseList(head);
        printLinkedList(newHead);
    }
}