package leetcode.middle;


/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * <p>
 * Example 2:
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * <p>
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class M328_OddEvenLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head, odd = new ListNode(0), oddHead = odd, even = new ListNode(0), evenHead = even;
        int i = 1;
        while (cur != null) {
            if (i % 2 != 0) {
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
            }
            cur = cur.next;
            i++;
        }
        //注意：最后 even.next 要置 null，如 1->2->3->null，不置 null 的话 2 仍指向 3
        even.next = null;
        odd.next = evenHead.next;
        return oddHead.next;
    }

    public ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        //注意条件
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
