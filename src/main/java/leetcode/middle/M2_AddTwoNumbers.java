package leetcode.middle;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p>
 * 参考 M445_AddTwoNumbersII
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

    //统一成一种情况：两个链表中只要有一个不为空行，由于链表可能为空，所以我们在取当前结点值的时候，先判断一下，若为空则取0，否则取结点值。然后把两个结点值相加，同时还要加上进位carry
    //https://www.cnblogs.com/grandyang/p/4129891.html
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1), cur = dummyHead;
        int sum = 0, carry = 0, d1, d2;
        //最高位的进位问题要最后特殊处理一下，若carry为1，则再建一个值为1的结点
        while (l1 != null || l2 != null || carry != 0) {
            // d1、d2代表两个加数
            d1 = l1 == null ? 0 : l1.val;
            d2 = l2 == null ? 0 : l2.val;
            sum = d1 + d2 + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        int[] arr2 = new int[]{9, 9};
        ListNode l1 = createLinkedList(arr, arr.length);
        ListNode l2 = createLinkedList(arr2, arr2.length);
        new M2_AddTwoNumbers().addTwoNumbers(l1, l2);
    }
}
