package sword.ch3;

import sword.ListNode;

/**
 * 删除链表节点
 *
 * @author Fighter Created on 2018/5/8.
 */
public class P119_DeleteNodeInList {
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || head.next == null || tobeDelete == null) {
            return null;
        }
        //待删除节点不是尾结点
        if (tobeDelete.next != null) {
            tobeDelete.val = tobeDelete.next.val;
            tobeDelete.next = tobeDelete.next.next;
        } else {
            //待删除节点是尾结点
            ListNode cur = head;
            while (cur.next != tobeDelete) {
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;

    }
}
