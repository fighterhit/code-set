package sword.ch4;

import sword.TreeNode;

/**
 * @author Fighter.
 * leetcode 108. Convert Sorted Array to Binary Search Tree https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * leetcode 109. Convert Sorted List to Binary Search Tree https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * <p>
 * https://www.cnblogs.com/grandyang/p/9615871.html
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
            //题目不允许创建新结点，考虑方法二递归
            result = new TreeNode[2];
            result[0] = treeLeft[0];
            result[1] = treeRight[1];
        }
        return result;
    }

    /**
     * 用一个变量pre，来记录上一个遍历到的结点。还需要一个变量head，来记录最左结点，这样的话，在递归函数中，先判空，之后对左子结点调用递归，这样会先一直递归到最左结点，此时如果head为空的话，说明当前就是最左结点，赋值给head和pre，对于之后的遍历到的结点，那么可以和pre相互连接上，然后pre赋值为当前结点node，再对右子结点调用递归即可。
     */
    TreeNode head = null, pre = null;

    public TreeNode Convert2(TreeNode root) {
        inOrder(root);
        return head;
    }

    //中序遍历
    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);

        if (head == null) {
            head = root;
            pre = root;
        } else {
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        inOrder(root.right);
    }
}
