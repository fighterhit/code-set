package sword.ch5;

import sword.ListNode;

public class P253_CommonNodesInLists {
    // a + c + b == b + c + a
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode pHeadA = pHead1, pHeadB = pHead2;


        while (pHeadA != pHeadB) {
            pHeadA = (pHeadA == null) ? pHead2 : pHeadA.next;
            pHeadB = (pHeadB == null) ? pHead1 : pHeadB.next;
            /*if (pHeadA == null) {
                pHeadA = pHead2;
            } else {
                pHeadA = pHeadA.next;
            }
            if (pHeadB == null) {
                pHeadB = pHead1;
            } else {
                pHeadB = pHeadB.next;
            }*/
        }
        return pHeadA;
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        ListNode<Integer> node7 = new ListNode<>(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode<Integer> commonNode = FindFirstCommonNode(node1, node4);
        System.out.println(commonNode.val);
        ListNode<Integer> commonNode2 = FindFirstCommonNode(node1, node4);
        System.out.println(commonNode2.val);
        ListNode<Integer> commonNode3 = FindFirstCommonNode(node1, node4);
        System.out.println(commonNode3.val);
    }
}
