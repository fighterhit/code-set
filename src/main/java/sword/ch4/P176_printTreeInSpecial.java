package sword.ch4;

import sword.TreeNode;

import java.util.*;

/**
 * 按照之字形打印二叉树
 *
 * @author Fighter.
 */
public class P176_printTreeInSpecial {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }

        Stack<TreeNode> lStack = new Stack<>();
        Stack<TreeNode> rStack = new Stack<>();
        lStack.push(pRoot);
        ArrayList<Integer> tmpRes;

        while (!lStack.isEmpty() || !rStack.isEmpty()) {
            //若左栈不为空
            if (!lStack.isEmpty()) {
                tmpRes = new ArrayList<>();
                //flag == true，从左往右打印
                while (!lStack.isEmpty()) {
                    TreeNode node = lStack.pop();
                    tmpRes.add((Integer) node.val);
                    if (node.left != null) {
                        rStack.push(node.left);
                    }
                    if (node.right != null) {
                        rStack.push(node.right);
                    }
                }
            }
            //flag == false，从右往左打印
            else {
                tmpRes = new ArrayList<>();
                //flag == true，从左往右打印
                while (!rStack.isEmpty()) {
                    TreeNode node = rStack.pop();
                    tmpRes.add((Integer) node.val);
                    if (node.right != null) {
                        lStack.push(node.right);
                    }
                    if (node.left != null) {
                        lStack.push(node.left);
                    }
                }
            }
            res.add(tmpRes);
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> Print2(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        //标志，true时从左到右，false时将那一行结果翻转一下
        boolean flag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> ls = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode t = q.poll();
                ls.add((int) t.val);
                if (t.left != null) {
                    q.add(t.left);
                }
                if (t.right != null) {
                    q.add(t.right);
                }
            }
            if (flag) {
                Collections.reverse(ls);
            }
            res.add(ls);
            flag = !flag;
        }
        return res;
    }

}
