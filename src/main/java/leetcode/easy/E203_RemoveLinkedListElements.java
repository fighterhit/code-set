package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example:
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class E203_RemoveLinkedListElements {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 递归法
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        /*head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;*/

        //上述两行代码等价于
        //看除去头结点后剩下链表处理结果
        ListNode res = removeElements(head.next, val);
        //再看头结点符不符合要求，符合要求连上后面链表，不符合直接返回后面链表处理结果
        if (head.val == val) {
            return res;
        } else {
            return head;
        }
    }

    //加入虚拟头结点，使删除逻辑统一
    public ListNode removeElements3(ListNode head, int val) {
        //虚拟头结点取值无所谓
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode curNode = dummyHead;
        while (curNode.next != null) {
            if (curNode.next.val == val) {
                ListNode delNode = curNode.next;
                curNode.next = delNode.next;
                delNode = null;
            } else {
                curNode = curNode.next;
            }
        }
        //返回虚拟头节点的下一个节点
        ListNode retNode = dummyHead.next;
        dummyHead = null;
        return retNode;
    }

    public ListNode removeElements2(ListNode head, int val) {
        //此判断可省略
        if (head == null) {
            return null;
        }
        //遍历直到不是val值元素
        while (head != null && head.val == val) {
            head = head.next;
        }
        //若遍历到链表末尾
        if (head == null) {
            return null;
        }
        //找到第一个满足要求节点
        ListNode tmp = head;
        while (tmp.next != null) {
            if (tmp.next.val == val) {
                //注意此时tmp指向不变
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }

    public ListNode removeElements1(ListNode head, int val) {
        List<ListNode> res = new ArrayList<>();
        while (head != null) {
            if (head.val != val) {
                res.add(head);
            }
            head = head.next;
        }
        ListNode newHead = new ListNode(-1), tail = newHead;
        for (int i = 0, size = res.size(); i < size; i++) {
            tail.next = res.remove(0);
            tail = tail.next;
        }
        tail.next = null;
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = null;
        new E203_RemoveLinkedListElements().removeElements(node1, 6);
    }

}
