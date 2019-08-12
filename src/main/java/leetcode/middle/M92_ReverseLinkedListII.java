package leetcode.middle;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * <p>
 * m,n超过范围？ m > n?
 */
public class M92_ReverseLinkedListII {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode curNode = dummyHead, preNode = null, tailNode = null, mNode = null, nNode = null;
        int i = 0;
        for (; i <= n; i++) {
            if (i == m - 1) {
                preNode = curNode;
                curNode = curNode.next;
                mNode = curNode;
            } else if (i == n) {
                nNode = curNode;
                curNode = curNode.next;
                tailNode = nNode.next;
                nNode.next = null;
            } else {
                curNode = curNode.next;
            }
        }
        reverse(mNode);
        preNode.next = nNode;
        mNode.next = tailNode;
        return dummyHead.next;
    }

    //返回尾节点tail
    ListNode reverse(ListNode head) {
        ListNode preNode = null, curNode = head, nextNode = null;
        while (curNode != null) {
            //循环内获取curNode后继
            nextNode = curNode.next;
            //反转curNode指针
            curNode.next = preNode;
            //迭代
            preNode = curNode;
            curNode = nextNode;
        }
        //curNode == null 退出循环
        return preNode;
    }

    public static ListNode createLinkedList(int[] arr, int n) {
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

    public static void printLinkedList(ListNode head) {
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

        ListNode newHead = new M92_ReverseLinkedListII().reverseBetween(head, 2, 4);
        printLinkedList(newHead);
    }
}
