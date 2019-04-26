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

    private TreeNode inOrder(TreeNode pRoot) {
        if (pRoot != null) {
            inOrder(pRoot.left);
            if (++i == k) {
                return pRoot;
            }
            inOrder(pRoot.right);
        }
        return null;
    }
}
