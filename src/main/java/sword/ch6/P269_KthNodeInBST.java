package sword.ch6;

import sword.TreeNode;

import java.util.Stack;

public class P269_KthNodeInBST {
    int i = 0;
    int k = 0;
    Stack<TreeNode> stack;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        this.k = k;
        //注意不用将 root 先访入栈中，否则会重复放入；在树的层序遍历时是将根节点先放入队列中
        stack = new Stack<>();
        while (!stack.isEmpty() || pRoot != null) {
            if (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            } else {
                pRoot = stack.pop();
                i++;
                if (i == k) {
                    break;
                }
                pRoot = pRoot.right;
            }
        }
        return pRoot;
    }

    //递归版中序遍历
    TreeNode ret;

    TreeNode KthNode2(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        helper(pRoot, k);
        return ret;
    }

    private void helper(TreeNode pRoot, int k) {
        /*
        //计数值超过 k 则直接返回
        if (pRoot == null || i >= k) {
            return;
        }
        helper(pRoot.left, k);
        i++;
        if (i == k) {
            ret = pRoot;
            return;
        }
        helper(pRoot.right, k);*/
        if (pRoot != null && i != k) {
            helper(pRoot.left, k);
            i++;
            if (i == k) {
                ret = pRoot;
                return;
            }
            helper(pRoot.right, k);
        }
    }
}
