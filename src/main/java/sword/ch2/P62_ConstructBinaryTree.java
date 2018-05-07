package sword.ch2;

import sword.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 *
 * 重建二叉树:
 * <p>
 * 前序+中序，后续+中序可以完成重建，而前序+后序无法完成
 *
 * @author Fighter Created on 2018/4/26.
 */
public class P62_ConstructBinaryTree {

    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length) {
            return null;
        }
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        //没有子节点
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIndex = map.get(pre[preStart]);
        //左子树元素个数：rootIndex - inStart
        //重建左子树
        root.left = reConstructBinaryTree(pre, preStart + 1, preStart + rootIndex - inStart, in, inStart, inStart + rootIndex - inStart);
        //重建右子树
        root.right = reConstructBinaryTree(pre, preStart + rootIndex - inStart + 1, preEnd, in, rootIndex + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {

    }
}
