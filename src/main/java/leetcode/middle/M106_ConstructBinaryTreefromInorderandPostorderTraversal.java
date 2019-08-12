package leetcode.middle;

import sword.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据中序和后序结果构造二叉树
 * 对比 M105_ConstructBinaryTreefromPreorderandInorderTraversal
 */
public class M106_ConstructBinaryTreefromInorderandPostorderTraversal {
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
//        return helper2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postEnd) {
        //注意这种只取 postEnd 方法边界条件！！！
        if (postEnd > inEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = inMap.get(postorder[postEnd]);
        root.right = helper(inorder, inIndex + 1, inEnd, postorder, postEnd - 1);
        root.left = helper(inorder, inStart, inIndex - 1, postorder, postEnd - (inEnd - inIndex) - 1);
        return root;
    }

    private TreeNode helper2(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = inMap.get(postorder[postEnd]);
        root.left = helper2(inorder, inStart, inIndex - 1, postorder, postStart, postStart + inIndex - inStart - 1);
        //注意右子树起始位置是从 postStart + inIndex - inStart 开始，不需要+1，因为根节点在后面，和 M105_ConstructBinaryTreefromPreorderandInorderTraversal 对比
        root.right = helper2(inorder, inIndex + 1, inEnd, postorder, postStart + inIndex - inStart, postEnd - 1);
        return root;
    }
}
