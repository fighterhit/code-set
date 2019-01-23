package leetcode.middle;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 */
public class M450_DeleteNodeinaBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root, pre = null;
        while (cur != null && cur.val != key) {
            pre = cur;
            if (cur.val > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        //当前树的根节点即为待删除节点
        if (pre == null) {
            return deleteNodeHelper(cur);
        }
        if (pre.left == cur) {
            pre.left = deleteNodeHelper(cur);
        } else {
            pre.right = deleteNodeHelper(cur);
        }
        return root;
    }

    TreeNode deleteNodeHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        //把待删节点的左子树 挂到 右子树中最小节点的左孩子
        TreeNode rightMin = findMin(root.right);
        rightMin.left = root.left;
        return root.right;
    }

    TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
