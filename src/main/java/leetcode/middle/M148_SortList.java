package leetcode.middle;

import java.util.List;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 题目限定了时间必须为O(nlgn)，符合要求只有快速排序，归并排序，堆排序，而根据单链表的特点，最适于用归并排序。
 * 这是由于链表自身的特点决定的，由于不能通过坐标来直接访问元素，所以快排什么的可能不太容易实现，堆排序的话，如果让新建结点的话，还是可以考虑的，若只能交换结点，最好还是不要用。
 * 而归并排序（又称混合排序）因其可以利用递归来交换数字，天然适合链表这种结构。归并排序的核心是一个 merge() 函数，其主要是合并两个有序链表，
 */
public class M148_SortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //链表归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return mergeSort(head);
//        return mergeSort(head, null);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode nextHead = mid.next;
        mid.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(nextHead);
        return merge(l, r);
    }

    //合并两个有序链表
    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (l != null && r != null) {
            if (l.val < r.val) {
                cur.next = l;
                l = l.next;
            } else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        if (l == null) {
            cur.next = r;
        } else {
            cur.next = l;
        }
        return dummy.next;
    }

    //找链表中点
    private ListNode findMid(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
   /* //其实 tail 是不必要的，因为当置 mid.next = null 后，两部分链表在递归过程中都以 null 结尾，和 M109_ConvertSortedListtoBinarySearchTree 需要用 tail 比较不同
    private ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) {
            return head;
        }
        ListNode mid = findMid(head, tail);
        ListNode nextHead = mid.next;
        mid.next = null;
        ListNode l = mergeSort(head, null);
        ListNode r = mergeSort(nextHead, tail);
        return merge(l, r);
    }*/


    //尾递归合并双链表，链表太长会栈溢出
    /*ListNode merge(ListNode l, ListNode r){
        if(l == null){
            return r;
        }
        if(r == null){
            return l;
        }

        if(l.val < r.val){
            l.next = merge(l.next, r);
            return l;
        }else{
            r.next = merge(l, r.next);
            return r;
        }
    }*/


    /**
     * 链表排序（冒泡、选择、插入、快排、归并、希尔、堆排序）
     * https://www.cnblogs.com/TenosDoIt/p/3666585.html
     */
    //算法只交换节点的val值，平均时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1）
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //注意区间：左闭右开
        quickSort(head, null);
        return head;
    }

    private void quickSort(ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) {
            return;
        }
        ListNode p = partition(head, tail);
        quickSort(head, p);
        quickSort(p.next, tail);
    }

    //交换值
    ListNode partition(ListNode low, ListNode high) {
        //链表范围是[low, high)
        int key = low.val;
        ListNode loc = low;
        for (ListNode i = low.next; i != high; i = i.next) {
            if (i.val < key) {
                loc = loc.next;
                swap(i, loc);
            }
        }
        swap(loc, low);
        return loc;
    }
    /*private ListNode partition(ListNode head, ListNode tail) {
        int pivot = head.val;
        ListNode loc = head, cur = head.next;
        while (cur != tail) {
            if (cur.val < pivot) {
                loc = loc.next;
                swap(loc, cur);
            }
            cur = cur.next;
        }
        swap(head, loc);
        return loc;
    }*/

    private void swap(ListNode loc, ListNode cur) {
        int t = loc.val;
        loc.val = cur.val;
        cur.val = t;
    }


    //链表快排，交换节点
    public ListNode sortList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        //注意区间：左闭右开
        //虚拟头节点 dummyHead 一直在最前面连接后面实际链表
        quickSort2(dummyHead, head, null);
        return dummyHead.next;
    }

    private void quickSort2(ListNode dummyHead, ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) {
            return;
        }
        ListNode p = partition2(dummyHead, head, tail);
        //注意：quickSort2(dummyHead, head, p); 不能再用 head，head 可能已经不再是链表头了
        quickSort2(dummyHead, dummyHead.next, p);
        quickSort2(p, p.next, tail);
    }

    //"交换节点"：分别构建小于 key 的链表和大于 key 的链表，再将两者连接起来
    ListNode partition2(ListNode dummyHead, ListNode low, ListNode high) {
        //链表范围是[low, high)
        int key = low.val;
        ListNode lDummyHead = new ListNode(-1), rDummyHead = new ListNode(-1);
        ListNode lCur = lDummyHead, rCur = rDummyHead;
        for (ListNode i = low.next; i != high; i = i.next) {
            if (i.val < key) {
                lCur.next = i;
                lCur = lCur.next;
            } else {
                rCur.next = i;
                rCur = rCur.next;
            }
        }
        //保证子链表 [low, high) 和后面部分连接
        rCur.next = high;
        lCur.next = low;
        low.next = rDummyHead.next;
        //保证子链表 [low,high) 和前面的部分连接
        dummyHead.next = lDummyHead.next;
        return low;
    }
}
