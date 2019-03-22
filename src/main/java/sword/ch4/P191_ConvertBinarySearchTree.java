package sword.ch4;

import sword.TreeNode;

/**
 * @author Fighter.
 * leetcode 108. Convert Sorted Array to Binary Search Tree https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * leetcode 109. Convert Sorted List to Binary Search Tree https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class P191_ConvertBinarySearchTree {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        return convertCore(pRootOfTree)[0];
    }

    //返回最小结点和最大结点
    TreeNode[] convertCore(TreeNode root) {
        TreeNode[] result;
        if (root.left == null && root.right == null) {
            result = new TreeNode[2];
            result[0] = root;
            result[1] = root;
        } else if (root.left == null) {
            result = convertCore(root.right);
            root.right = result[0];
            //返回值的最大值节点为root
            result[0].left = root;
            result[0] = root;
        } else if (root.right == null) {
            result = convertCore(root.left);
            root.left = result[1];
            result[1].right = root;
            result[1] = root;
        } else {
            TreeNode[] treeLeft, treeRight;
            treeLeft = convertCore(root.left);
            treeRight = convertCore(root.right);

            root.left = treeLeft[1];
            root.right = treeRight[0];
            treeLeft[1].right = root;
            treeRight[0].left = root;

            result = new TreeNode[2];
            result[0] = treeLeft[0];
            result[1] = treeRight[1];
        }
        return result;
    }
}
