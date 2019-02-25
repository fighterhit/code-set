package sword.ch3;

import sword.ListNode;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 * @author Fighter Created on 2018/5/9.
 */
public class P122_deleteDuplicatedNode {
    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode preNode = null;
        ListNode pNode = pHead;
        ListNode next = null;
        boolean needDel = false;

        while (pNode != null) {
            next = pNode.next;
            //若需要删除，则修改标记
            if (next != null && next.val == pNode.val) {
                needDel = true;
            }
            //若不需要删除，则当前节点作为父节点，下节点作为当前节点
            if (!needDel) {
                preNode = pNode;
                pNode = pNode.next;
            }
            //若要删除，则开始遍历后面节点
            else {

                int val = (int) pNode.val;
                ListNode pToBeDelNode = pNode;

                while (pToBeDelNode != null && (int) pToBeDelNode.val == val) {
                    next = pToBeDelNode.next;
                    pToBeDelNode = next;
                }

                if (preNode == null) {
                    pHead = next;
                } else {
                    preNode.next = next;
                }
                pNode = next;
                needDel = false;
            }
        }
        return pHead;
    }


    public static ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            //头结点是重复节点
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication(next);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        head.next = head1;
        ListNode head2 = new ListNode(3);
        head1.next = head2;
        ListNode head3 = new ListNode(3);
        head2.next = head3;
        ListNode head4 = new ListNode(4);
        head3.next = head4;
        ListNode head5 = new ListNode(4);
        head4.next = head5;
        ListNode head6 = new ListNode(5);
        head5.next = head6;
        head5 = new ListNode(55);
//        deleteDuplication(head);
        System.out.println(head);
    }
}
