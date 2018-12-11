package leetcode.middle;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
 * @author Fighter.
 */
public class M445_AddTwoNumbersII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //法1：可以先翻转，按M2_AddTwoNumbers方法相加再将结果翻转
        /*ListNode newL1 = reverse(l1);
        ListNode newL2 = reverse(l2);
        return reverse(add(newL1, newL2));*/

        //法2：利用栈实现“翻转”
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();
        while (l1 != null) {
            l1Stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Stack.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode curNode = new ListNode(0);
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty()) {
            if (!l1Stack.isEmpty()) {
                sum += l1Stack.pop();
            }
            if (!l2Stack.isEmpty()) {
                sum += l2Stack.pop();
            }
            curNode.val = sum % 10;
            ListNode pre = new ListNode(sum / 10);
            pre.next = curNode;
            //迭代
            curNode = pre;
            sum /= 10;
        }
        //注意略去前导0
        return curNode.val == 0 ? curNode.next : curNode;
    }
}
