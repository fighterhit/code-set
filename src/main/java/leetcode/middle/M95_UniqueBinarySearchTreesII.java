package leetcode.middle;

import sword.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * <p>
 * Example:
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * 建树问题一般来说都是用递归来解，这道题也不例外，划分左右子树，递归构造。这个其实是用到了大名鼎鼎的分治法 Divide and Conquer
 * 刚开始时，我们将区间 [1, n] 当作一个整体，然后我们需要将其中的每个数字都当作根结点，其划分开了左右两个子区间，然后分别调用递归函数，会得到两个结点数组，接下来要做的就是从这两个数组中每次各取一个结点，当作当前根结点的左右子结点，然后将根结点加入结果 res 数组中即可
 * https://www.cnblogs.com/grandyang/p/4301096.html
 */
public class M95_UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new LinkedList<>();
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int l, int h) {
        List<TreeNode> res = new LinkedList<>();
        //返回条件！！！
        if (l > h) {
            res.add(null);
            return res;
        }
        //i 为根节点
        for (int i = l; i <= h; i++) {
            //left 保存左子树所有情况
            List<TreeNode> left = helper(l, i - 1);
            //right 保存右子树所有情况
            List<TreeNode> right = helper(i + 1, h);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new M95_UniqueBinarySearchTreesII().generateTrees(3);
    }
}
