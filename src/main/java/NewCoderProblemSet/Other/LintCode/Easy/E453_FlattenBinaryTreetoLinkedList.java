package NewCoderProblemSet.Other.LintCode.Easy;

import sword.TreeNode;

/**
 * 将一棵二叉树按照前序遍历拆解成为一个 假链表。所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
 */
public class E453_FlattenBinaryTreetoLinkedList {
    TreeNode pre;

    public void flatten(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }
        //因为是前序遍历，该步会改变父节点右指针指向，因此下面需要先将右孩子保存下来
        if (pre != null) {
            pre.right = root;
            pre.left = null;
        }
        pre = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
