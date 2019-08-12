package NewCoderProblemSet.InAdvance;

import sword.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Vivo20190604 {

    /**
     * 输出在数组A但不在数组B中的元素
     */
    static class Solution1 {

        public static void main(String[] args) {
            int[] arrA = new int[]{1, 2, 3, 4, 5};
            int[] arrB = new int[]{2, 4};
            solution(arrA, arrB);
        }

        public static void solution(int[] arrA, int[] arrB) {
            Set<Integer> bs = new HashSet<>();
            for (int i : arrB) {
                bs.add(i);
            }
            for (int i = 0; i < arrA.length; i++) {
                if (!bs.contains(arrA[i])) {
                    if (i != arrA.length - 1) {
                        System.out.print(arrA[i] + " ");
                    } else {
                        System.out.print(arrA[i]);
                    }
                }
            }
        }
    }

    /**
     * 反转指定区间的链表 M92_ReverseLinkedListII
     */
    public static class Solution2 {
        public static void solution(ListNode head, int m, int n) {

            if (head == null || head.next == null) {
                printLinkedList(head);
            }

            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode cur = dummyHead;

            //pre 保存 mNode 前的节点，方便后续连接；tail 保存 nNode 后一节点
            ListNode pre = null, tail = null, mNode = null, nNode = null;

            for (int i = 0; i <= n; i++) {
                //到达
                if (i == m - 1) {
                    pre = cur;
                    cur = cur.next;
                    mNode = cur;
                } else if (i == n) {
                    nNode = cur;
                    tail = cur.next;
                    nNode.next = null;
                } else {
                    cur = cur.next;
                }
            }

            reverse(mNode);
            pre.next = nNode;
            mNode.next = tail;

            printLinkedList(dummyHead.next);
        }

        public static void solution2(ListNode head, int m, int n) {

            if (head == null || head.next == null) {
                printLinkedList(head);
            }

            ListNode dummyHead = new ListNode(-1), cur = dummyHead;
            dummyHead.next = head;

            //pre 保存 mNode 前的节点，方便后续连接；tail 保存 nNode 后一节点
            ListNode pre = null, tail = null, mNode = null, nNode = null;

            for (int i = 0; cur != null && i <= n; i++) {
                //到达 mNode 前一个节点
                if (i == m - 1) {
                    pre = cur;
                    cur = cur.next;
                    mNode = cur;
                } else if (i == n) {
                    nNode = cur;
                    tail = nNode.next;
                    nNode.next = null;
                    cur = cur.next;
                } else {
                    cur = cur.next;
                }
            }

            reverse(mNode);
            pre.next = nNode;
            mNode.next = tail;

            printLinkedList(dummyHead.next);
        }

        static ListNode reverse(ListNode head) {
            ListNode pre = null, cur = head, next = null;
            while (cur != null) {
                next = cur.next;

                //每次改变当前节点 cur 的指向
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        static ListNode createLinkedList(int[] arr, int n) {
            if (n == 0) {
                return null;
            }
            ListNode head = new ListNode(arr[0]);
            ListNode curNode = head;
            for (int i = 1; i < n; i++) {
                curNode.next = new ListNode(arr[i]);
                curNode = curNode.next;
            }
            return head;
        }

        static void printLinkedList(ListNode head) {
            ListNode curNode = head;
            while (curNode != null) {
                System.out.print(curNode.val + " ->");
                curNode = curNode.next;
            }
            System.out.println("null");
        }

        public static void main(String[] args) {
            //[3, 5], 1, 1
            //[3, 5], 1, 2
            int[] arr = new int[]{3, 5};
            int n = arr.length;
            ListNode head = createLinkedList(arr, n);
            Solution2.solution2(head, 1, 1);
        }
    }

    /**
     * 01背包
     */
    public static class Solution3 {
        public static void solution3(int C, int[] weight, int[] value) {
            int n = weight.length;
            //0 ~ C 列一共 C+1 列
            int[][] memo = new int[n][C + 1];
            for (int i = 0; i < memo[0].length; i++) {
                memo[0][i] = i >= weight[0] ? value[0] : 0;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < memo[0].length; j++) {
                    memo[i][j] = memo[i - 1][j];
                    if (j >= weight[i]) {
                        memo[i][j] = Math.max(memo[i][j], memo[i - 1][j - weight[i]] + value[i]);
                    }
                }
            }
            System.out.println(memo[n - 1][C]);
        }

        public static void main(String[] args) {
            int C = 20;
            int[] weight = new int[]{8, 7, 6, 4};
            int[] value = new int[]{14, 15, 20, 9};
            solution3(C, weight, value);
        }
    }
}
