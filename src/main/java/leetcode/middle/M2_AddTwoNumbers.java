package leetcode.middle;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @author Fighter.
 */
public class M2_AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Head = l1, l2Head = l2, res = new ListNode(0), resHead = res;
        int tmpRes = 0;
        while (l1Head != null && l2Head != null) {
            tmpRes += l1Head.val + l2Head.val;
            res.next = new ListNode(tmpRes % 10);
            tmpRes /= 10;
            l1Head = l1Head.next;
            l2Head = l2Head.next;
            res = res.next;
        }
        while (l1Head != null) {
            tmpRes += l1Head.val;
            res.next = new ListNode(tmpRes % 10);
            tmpRes /= 10;
            l1Head = l1Head.next;
            res = res.next;
        }
        while (l2Head != null) {
            tmpRes += l2Head.val;
            res.next = new ListNode(tmpRes % 10);
            tmpRes /= 10;
            l2Head = l2Head.next;
            res = res.next;
        }
        if (tmpRes != 0) {
            res.next = new ListNode(tmpRes);
        }
        return resHead.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        int[] arr2 = new int[]{9, 9};
        ListNode l1 = createLinkedList(arr, arr.length);
        ListNode l2 = createLinkedList(arr2, arr2.length);
        new M2_AddTwoNumbers().addTwoNumbers(l1, l2);
    }
}
