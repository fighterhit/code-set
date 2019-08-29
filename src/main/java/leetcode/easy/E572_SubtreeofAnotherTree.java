package leetcode.easy;

import sword.TreeNode;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * 参考 E437_PathSumIII 写法，都是先判断当前节点，当前节点不行再判断左右子树
 * 对比 E101_SymmetricTree
 */
public class E572_SubtreeofAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return helper(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    //查看当前节点为根时，t是否为子树
    private boolean helper(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return helper(s.left, t.left) && helper(s.right, t.right);
    }
}

class CloneExample implements  Cloneable{
    private int a;
    private int b;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneExample e1 = new CloneExample();
        CloneExample e2 = (CloneExample) e1.clone();
    }
}
