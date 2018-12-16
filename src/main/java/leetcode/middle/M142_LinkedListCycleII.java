package leetcode.middle;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 *
 * @author Fighter.
 *
 * 参考 E141_LinkedListCycle，先判断是否有环，若有环根据推算得到公式：len(head to the node of circle) = n*len(circle) + len(intersection node to circle begin node)
 * https://huatj0210.github.io/2018/03/11/LeetCode-%E5%8D%95%E9%93%BE%E8%A1%A8%E6%9F%A5%E6%89%BE%E7%8E%AF%E9%97%AE%E9%A2%98/
 */
public class M142_LinkedListCycleII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            //无环返回null，不能返回head
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //肯定有环，并且快指针走过的路程是慢指针的两倍
            if (fast == slow) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return head;
            }
        }
        //无环
        return null;
    }
}
