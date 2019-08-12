package leetcode.hard;

import sword.TreeNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * <p>
 * Example 1:
 * Input: [1,3,null,null,2]
 * 1
 * /
 * 3
 * \
 * 2
 * Output: [3,1,null,null,2]
 * 3
 * /
 * 1
 * \
 * 2
 * Example 2:
 * Input: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 * /
 * 2
 * Output: [2,1,4,null,null,3]
 * 2
 * / \
 * 1   4
 * /
 * 3
 * Follow up:
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class H99_RecoverBinarySearchTree {
    //中序遍历：分别得到节点和值两个列表，将值列表排完序依次赋值给节点列表即可
    //O(n) 空间复杂度
    List<TreeNode> nList = new LinkedList<>();
    List<Integer> vList = new LinkedList<>();

    public void recoverTree(TreeNode root) {
        vList.sort(Comparator.comparingInt(o -> o));
        System.out.println(vList);
        for (int i = 0; i < nList.size(); i++) {
            nList.get(i).val = vList.get(i);
        }
    }

    void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.left);
            nList.add(root);
            vList.add((int) root.val);
            dfs(root.right);
        }
    }

    //题目要求的 O(1) 空间复杂度方法
    public void recoverTree2(TreeNode root) {

    }
}
