package leetcode.middle;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Example 1:
 * Input:
 * 2
 * / \
 * 1   3
 * Output: true
 * <p>
 * Example 2:
 * 5
 * / \
 * 1  4
 * / \
 * 3   6
 * Output: false
 * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 * is 5 but its right child's value is 4.
 *
 * @author Fighter.
 * <p>
 * Property of a binary search tree
 * - root.val > all vals in L
 * - root.val < all vals in R
 * - Inorder traversal, vals are sorted
 * <p>
 * http://www.cnblogs.com/yuzhangcmu/p/4177047.html
 * https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
 */
public class M98_ValidateBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        //with long，存在用例 [-2147483648,null,2147483647]
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        //without long
        return isValidBST2(root, null, null);
    }

    //with long
    boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    //without long
    boolean isValidBST2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (max != null && root.val >= max.val || min != null && root.val <= min.val) {
            return false;
        }
        return isValidBST2(root.left, min, root) && isValidBST2(root.right, root, max);
    }

    //非递归：栈
    //用栈的话，先寻找最左边的节点，把经过的节点都存入栈中，第一个被弹出来的为最左节点，那么访问其右子树，对右子树也像前面一样遍历，整个流程跟递归一样。
    boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode cur = root, pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            //栈空退出
            if (stack.isEmpty()) {
                return true;
            }

            cur = stack.pop();
            //pre: 左边节点 cur: 右边节点
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
    }


    /**
     * 非递归：栈，出栈顺序即中序遍历顺序，注意这个结构，M94_BinaryTreeInorderTraversal / M230_KthSmallestElementinaBST / M98_ValidateBinarySearchTree
     * 二叉树中序遍历 BinaryTreeTraverse.java ，只是比原方法多保存一个上一次访问的节点
     */
    boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && pre.val >= root.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
        }
        return true;
    }
}
