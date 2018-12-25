package leetcode.easy;

import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * Input: 1->2
 * Output: false
 * <p>
 * Example 2:
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 * @author Fighter.
 */
public class E234_PalindromeLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //找中点
        ListNode mid = findMid(head);
        ListNode l1 = head, l2 = mid.next;
        mid.next = null;
        //翻转后半段链表
        l2 = reverse(l2);
        while (l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    ListNode findMid(ListNode head) {
        //找中点，翻转后段链表
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 递归需要两个重要条件，递归步的建立和递归终止条件。对于回文比较，理所当然应该递归比较第 i 个节点
     * 和第 n-i 个节点，那么问题来了，如何构建这个递归步？大致可以猜想出来递归的传入参数应该包含两个节
     * 点，用以指代第 i 个节点和第 n-i 个节点。返回参数应该包含布尔值(用以提前返回不是回文的情况)和左半
     * 部分节点的下一个节点(用以和右半部分的节点进行比较)。由于需要返回两个值，在 Java 中需要使用自定
     * 义类进行封装，C/C++ 中则可以使用指针改变在递归调用后进行比较时节点的值。
     * <p>
     * 核心代码为如何在递归中推进左半部分节点而对右半部分使用栈的方式逆向获取节点。左半部分的推进需
     * 要借助辅助数据结构 Result
     * <p>
     * 递归调用 n 层，时间复杂度近似为 O(n), 使用了几个临时变量，空间复杂度为 O(1).
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Result result = new Result(head, true);
        helper(head, result);
        return result.isP;
    }

    private void helper(ListNode head, Result result) {
        if (head != null && !result.visit) {
            //一直递归到尾结点
            helper(head.next, result);
            boolean equal = head.val == result.lNode.val;
            result.isP = equal && result.isP;
            result.lNode = result.lNode.next;
            result.visit = true;
        }
    }

    class Result {
        ListNode lNode;
        boolean isP;
        boolean visit;

        Result(ListNode node, boolean isP) {
            this.lNode = node;
            this.isP = isP;
        }
    }

    //栈：压入前半段，依次弹出和后半段比较
    public boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode fast = head, slow = head;
        stack.push(slow);
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            stack.push(slow);
        }
        if (fast.next == null) {
            stack.pop();
        }
        ListNode right = slow.next;
        while (right != null) {
            if (stack.pop().val != right.val) {
                return false;
            }
            right = right.next;
        }
        return true;
    }
}
