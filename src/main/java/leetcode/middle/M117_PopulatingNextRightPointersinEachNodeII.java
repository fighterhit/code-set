package leetcode.middle;

/**
 * Given a binary tree
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * <p>
 * 参考 M116_PopulatingNextRightPointersinEachNode
 */
public class M117_PopulatingNextRightPointersinEachNodeII {
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
     * 自上而下递归，先连上面后连下面
     * 思路： 自顶向上递归构建next，分为以下几步：
     * 构建当前节点的左节点：
     * 1.1. 如果root有左节点和右节点，则左节点的Next为右节点
     * 1.2. 如果root右节点为Null，则查找父节点的兄弟节点的最左边子元素
     * 构建当前节点的右节点：
     * 2.1. 如果root右节点不为Null，则next为父节点的兄弟节点的最左边子元素
     * 递归：这里注意一定要先构建右子树，再构建左子树，因为寻找父节点的兄弟节点是从左到右遍历的，如果右子树未构建好就遍历，则会出错
     * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/java-di-gui-ji-bai-100-by-zxy0917/
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        //连左孩子的边
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = findNextChild(root.next);
            }
        }
        //连右孩子的边
        if (root.right != null) {
            root.right.next = findNextChild(root.next);
        }
        //递归：这里注意一定要先构建右子树，再构建左子树，因为寻找父节点的兄弟节点是从左到右遍历的，如果右子树未构建好就遍历，则会出错
        dfs(root.right);
        dfs(root.left);
    }

    private Node findNextChild(Node root) {
        while (root != null) {
            if (root.left != null) {
                return root.left;
            }
            if (root.right != null) {
                return root.right;
            }
            root = root.next;
        }
        return null;
    }

    /**
     * 非递归：迭代方法
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node head = root, dummy = new Node(0, null, null, null), cur = dummy;
        while (root != null) {
            if (root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }

            root = root.next;
            //root 为 null 当前层遍历完，root 置为下层首节点
            if (root == null) {
                cur = dummy;
                //dummy.next 通过 cur 来保存每行首节点
                root = dummy.next;
                //注意清空 dummy.next
                dummy.next = null;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] dp = new int[n][];
        System.out.println(dp[1].length);

    }
}
