package leetcode.middle;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * 注意，这里中序遍历的结果可能生成多个BST，对比 M449_SerializeandDeserializeBST
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  对比数组 E108_ConvertSortedArraytoBinarySearchTree
 */
public class M109_ConvertSortedListtoBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        //tail 是null，非最后一个节点
        return sortedListToBST(head, null);
    }

    private TreeNode sortedListToBST(ListNode head, ListNode tail) {
        //注意边界条件！！！
        if (head == tail) {
            return null;
        }
        ListNode fast = head, slow = head;
        //注意这里是与 tail 作比较不是 null，和 null 一样 tail 是取不到的，单链表找中点是与null比较
        while (fast.next != tail && fast.next.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head, slow);
        root.right = sortedListToBST(slow.next, tail);
        return root;
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
        int[] arr = new int[]{-10,-3,0,5,9};
        int n = arr.length;
        ListNode head = createLinkedList(arr, n);
        printLinkedList(head);
        new M109_ConvertSortedListtoBinarySearchTree().sortedListToBST(head);
    }
}
