package leetcode.easy;

/**
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * @author Fighter.
 *
 * 快指针走到 NULL 退出循环即可确定此链表一定无环这个很好理解。那么带环的链表快慢指针一定会相遇
 * 吗？在有环的情况下，最终快慢指针一定都走在环内，假如第 i 次遍历时快指针还需要 k 步才能追上慢指
 * 针，由于快指针比慢指针每次多走一步。那么每遍历一次快慢指针间的间距都会减少 1，直至最终相遇。故
 * 快慢指针相遇一定能确定该链表有环。
 *
 * 假设环的长度为 R，当慢指针walker走到环入口时，快指针runner在环的某个位置，且二者之间的距离为S。在慢指针进入环后的 t 时间内，快指针从距离环入口 S 处走了 2t 个节点，相当于从环入口走了 S+2t 个节点。而此时慢指针从环入口走了 t 个节点。假设快慢指针一定可以相遇，那么有 S+2t−t=nR，即 S+t=nR，如果对于任意的S，R，n，总可以找到一个t满足上式，那么就可以说明快慢指针一定可以相遇，满足假设(显然可以找到)
 * 而实际上，由于S<R，所以在慢指针走过一圈之前就可以相遇
 * 所以如果链表中有环，那么当慢指针进入到环时，在未来的某一时刻，快慢指针一定可以相遇，通过这个也就可以判断链表是否有环
 * 原文：https://blog.csdn.net/sinat_35261315/article/details/79205157
 */
public class E141_LinkedListCycle {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
