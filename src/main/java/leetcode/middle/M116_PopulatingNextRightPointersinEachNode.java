package leetcode.middle;


import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space. 常数空间
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem. 递归隐式空间不算额外空间
 * <p>
 * 完美二叉树 == 满二叉树
 */
public class M116_PopulatingNextRightPointersinEachNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 自上而下递归，对于每层节点，都是先将左孩子指向右孩子，然后再处理右孩子（若父节点存在next，则将右孩子指向父节点next节点的左孩子）
     * 由于是完全二叉树，所以若节点的左子结点存在的话，其右子节点必定存在，所以左子结点的next指针可以直接指向其右子节点.
     * 对于其右子节点的处理方法是，判断其父节点的next是否为空，若不为空，则指向其next指针指向的节点的左子结点，若为空则指向 NULL
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }

    void dfs(Node root) {
        if (root.left == null && root.right == null) {
            return;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * 用两个指针 start 和 cur，其中 start 标记每一层的起始节点，cur用来遍历该层的节点
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root, cur = null;
        while (start.left != null) {
            cur = start;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
        return root;
    }

    /**
     * O(n) 空间复杂度，不符合题意
     * 非递归层序遍历，每层的节点都按顺序加入queue中，而每当从queue中取出一个元素时，将其next指针指向queue中下一个节点即可
     */
    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (i != size - 1) {
                    node.next = q.peek();
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return root;
    }
}
