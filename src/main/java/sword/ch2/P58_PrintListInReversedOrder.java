package sword.ch2;

import sword.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表（链表翻转）
 *
 * @author Fighter Created on 2018/4/18.
 */

/**
 * public class ListNode {
 * int val;
 * ListNode next = null;
 * <p>
 * ListNode(int val) {
 * this.val = val;
 * }
 * }
 */
public class P58_PrintListInReversedOrder {
    // 栈
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ListNode ln = listNode;
        while (ln != null) {
            stack.add((int) ln.val);
            ln = ln.next;
        }
        ArrayList<Integer> ls = new ArrayList<>();
        while (!stack.isEmpty()) {
            ls.add(stack.pop());
        }
        return ls;
    }

    // 非递归
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {

        ListNode prev = null;
        while (listNode != null) {
            ListNode tmp = listNode.next;
            listNode.next = prev;
            prev = listNode;
            listNode = tmp;
        }

        ArrayList<Integer> ls = new ArrayList<>();
        while (prev != null) {
            ls.add((Integer) prev.val);
            prev = prev.next;
        }
        return ls;
    }

    // 递归 https://blog.csdn.net/fx677588/article/details/72357389
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode prev = recursiveLinkList(listNode);
        ArrayList<Integer> ls = new ArrayList<>();
        while (prev != null) {
            ls.add((Integer) prev.val);
            prev = prev.next;
        }
        return ls;
    }

    static ListNode recursiveLinkList(ListNode listNode) {
        // 尾结点
        if (listNode.next == null) {
            return listNode;
        }
        //recursiveLinkList 递归到最后一个节点，返回最后一个节点
        ListNode node = recursiveLinkList(listNode.next);
        //将当前节点的下一个结点指向自己
        listNode.next.next = listNode;
        //断开链表，防止形成环
        listNode.next = null;
        //返回最后一个节点
        return node;
    }

    /*public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next= n3;
        System.out.println(n1);
        printListFromTailToHead(n1);
        System.out.println(n3);

    }*/

    static void printParenthesis(int pos, int n, int open, int close, char[] buffer) {
//        System.out.println("step"+pos+" open is : "+ open + "close is :" + close);
//        System.out.println(new String(buffer));
        if (close == n) {
            System.out.println("over");
            System.out.println(new String(buffer));

            return;
        }
        if (open > close) {
            buffer[pos] = '}';
            printParenthesis(pos + 1, n, open, close + 1, buffer);

        }

        if (open < n) {
            buffer[pos] = '{';
            printParenthesis(pos + 1, n, open + 1, close, buffer);
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //System.out.println("012142");
        int n = 4;
        char[] cs = new char[8];
        printParenthesis(0, 4, 0, 0, cs);
        //System.out.println("012143");
    }
}
