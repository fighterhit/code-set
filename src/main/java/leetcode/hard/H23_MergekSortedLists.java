package leetcode.hard;

import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * Example:
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * @author Fighter.
 */
public class H23_MergekSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
       /*
       //注意 new Comparator<ListNode>() 没有参数，重写 compare 方法
       PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                if (node1.val < node2.val) {
                    return -1;
                } else if (node1.val > node2.val) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });*/
//        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(n1 -> n1.val));
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists) {
            //注意判断非空！！！
            //ListNode长度为1，第1个元素为null：[[]]
            //ListNode长度为2，第1个元素为null，第2个元素为链表[1]： [[],[1]]
            if (node != null) {
                queue.add(node);
            }
        }

        ListNode head = new ListNode(-1), tail = head;
        while (!queue.isEmpty()) {
            ListNode tmp = queue.poll();
            tail.next = tmp;
            tail = tmp;
            tmp = tmp.next;
            //注意判空
            if (tmp != null) {
                queue.add(tmp);
            }
        }
        return head.next;
    }

    /**
     * https://algorithm.yuanbin.me/zh-hans/linked_list/merge_k_sorted_lists.html
     * 基于合并两个有序链表的二分法，减少用for循环合并，链表被合并 k-i 次（如题解2），二分每个合并降到 logK 次
     * 从中间索引处进行二分归并后，每个链表参与合并的次数变为 logk 次, 故总的时间复杂度可降至 logk * sum(l_i) (i=1,2...k)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return helper(lists, 0, lists.length - 1);
    }

    private ListNode helper(ListNode[] lists, int l, int r) {
        //分两种边界条件处理，分别是 l == r 和 l + 1 == r. 虽然第二种边界条件可以略去，但是加上会节省递归调用的栈空间。
        if (l == r) {
            return lists[l];
        }
        if (l + 1 == r) {
            return merge2Lists(lists[l], lists[r]);
        }
        int m = l + (r - l >> 1);
        ListNode left = helper(lists, l, m);
        ListNode right = helper(lists, m + 1, r);
        return merge2Lists(left, right);
    }

    private ListNode merge2Lists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), tail = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = list1 == null ? list2 : list1;
        return head.next;
    }
}
