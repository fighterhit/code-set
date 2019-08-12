package leetcode.middle;

import sword.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 *
 * @author Fighter Created on 2018/5/9.
 * <p>
 * 参考 E83_RemoveDuplicatesfromSortedList，只保留重复元素中的一个
 */
public class M82_RemoveDuplicatesfromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preNode = null;
        ListNode pNode = head;
        ListNode next = null;
        boolean needDel = false;

        while (pNode != null) {

            next = pNode.next;
            if (next != null && next.val == pNode.val) {
                needDel = true;
            }

            if (!needDel) {
                preNode = pNode;
                pNode = pNode.next;
            } else {

                int val = (Integer) pNode.val;
                ListNode toBeDelNode = pNode;

                while (toBeDelNode != null && (Integer) toBeDelNode.val == val) {
                    next = toBeDelNode.next;
                    toBeDelNode = next;
                }

                if (preNode == null) {
                    head = next;
                } else {
                    preNode.next = next;
                }
                needDel = false;
                pNode = next;
            }

        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, curNode = head, next;
        dummyHead.next = head;
        while (curNode != null) {
            //每次进入循环再计算后继节点
            next = curNode.next;
            //当前节点和后继节点值相等时，两指针一直向后移直到相邻两节点值不同
            while (next != null && next.val == curNode.val) {
                curNode = next;
                next = next.next;
            }
            //若前驱节点和当前节点相邻，则说明当前节点不是重复节点，将前驱和当前节点后移
            if (pre.next == curNode) {
                pre = curNode;
                curNode = next;
            } else {
                curNode = next;
                pre.next = next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 思想同上:
     * 和 E83_RemoveDuplicatesfromSortedList 不同的地方是这里要删掉所有的重复项，由于链表开头可能会有重复项，被删掉的话头指针会改变，而最终却还需要返回链表的头指针。所以需要定义一个新的节点，然后链上原链表。
     * 定义一个 前驱指针 和一个 现指针，每当前驱指针指向新建的节点，现指针从下一个位置开始往下遍历，遇到相同的则继续往下，直到遇到不同项时，把前驱指针的 next 指向下面那个不同的元素。如果现指针遍历的第一个元素就不相同，则把前驱指针向下移一位。
     * https://www.cnblogs.com/grandyang/p/4069003.html
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0), pre = dummyHead, cur = null;
        dummyHead.next = head;
        while (pre.next != null) {
            //每次进入循环再计算后继节点
            cur = pre.next;
            //cur 指向最后一个相同值的节点
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            //若前驱节点和当前节点相邻，则说明当前节点不是重复节点，将前驱和当前节点后移
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * P122_deleteDuplicatedNode
     */
    public ListNode deleteDuplicates4(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && next.val == pHead.val) {
                next = next.next;
            }
            return deleteDuplicates(next);
        } else {
            pHead.next = deleteDuplicates(next);
            return pHead;
        }
    }


    public ListNode deleteDuplicates5(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = pHead;
        while (cur != null) {
            map.put((int) cur.val, map.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }
        ListNode dummyHead = new ListNode(-1);
        cur = dummyHead;
        while (pHead != null) {
            if (map.get(pHead.val) == 1) {

            }
        }
        return null;
    }
}
