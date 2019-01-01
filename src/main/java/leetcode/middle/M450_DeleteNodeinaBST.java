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
        TreeNode tmp = root, node;
        while (true) {
            if (root == null) {
                return null;
            }
            if (key < root.val) {
                root = root.left;
            } else if (key > root.val) {
                root = root.right;
            } else {
                node = root;
                break;
            }
        }
        
    }
}
