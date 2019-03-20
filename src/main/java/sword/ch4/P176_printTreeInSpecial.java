package sword.ch4;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
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
}
