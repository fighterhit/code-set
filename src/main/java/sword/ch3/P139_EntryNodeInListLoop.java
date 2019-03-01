package sword.ch3;

import sword.ListNode;

/**
 * 找环的入口节点
 * 1.判断时候有环（链表头指针为head）
 * 对于这个问题我们可以采用“快慢指针”的方法。就是有两个指针fast和slow，开始的时候两个指针都指向链表头head，然后在每一步
 * 操作中slow向前走一步即：slow = slow->next，而fast每一步向前两步即：fast = fast->next->next。
 * 由于fast要比slow移动的快，如果有环，fast一定会先进入环，而slow后进入环。当两个指针都进入环之后，经过一定步的操作之后
 * 二者一定能够在环上相遇，并且此时slow还没有绕环一圈，也就是说一定是在slow走完第一圈之前相遇。
 *
 * @author Fighter.
 */
public class P139_EntryNodeInListLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            //无环返回null，不能返回pHead
            return null;
        }
        ListNode fast = pHead, slow = pHead;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //相遇说明有环
            if (fast == slow) {
                slow = pHead;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
