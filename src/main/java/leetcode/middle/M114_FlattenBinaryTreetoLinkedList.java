package leetcode.middle;

import sword.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 */
public class M114_FlattenBinaryTreetoLinkedList {
    /**
     * 先调整右子树再调整左子树
     */
    TreeNode pre;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    /**
     * 按正常的先序遍历顺序，先左子树后右子树
     * 三步曲：1）右边接到左边的后面 2）左边接到 root.right 3）左边设为 null
     * <p>
     * 接下来每次都需要返回当前子树的最后一个节点，用于接右子树第一个节点，分三种情况：
     * 1) 当最右节点 rightLast != null，返回 rightLast
     * 2）最左节点 leftLast != null，返回 leftLast
     * 3）rightLast == null && leftLast == null，则返回 root
     * <p>
     * https://www.youtube.com/watch?v=pVOVJ8F_A_A
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }

    TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = dfs(root.left);
        TreeNode rightLast = dfs(root.right);
        //三步曲：1)右边接到左子树最后一个节点后面 2)左边接到 root.right 3)左边设为 null
        if (leftLast != null) {
            leftLast.right = root.right;
            //注意这里将左子树接在根节点右孩子上,然后将左孩子设为 null
            root.right = root.left;
            root.left = null;
        }
        //每次都需要返回当前子树的最后一个节点，用于接右子树第一个节点，分三种情况：
        //当最右节点 rightLast != null，返回 rightLast
        //最左节点 leftLast != null，返回 leftLast
        //rightLast == null && leftLast == null，则返回 root
        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        return root;
    }

    /**
     * 根据展开后形成的链表的顺序分析出是使用先序遍历，那么只要是数的遍历就有递归和非递归的两种方法来求解。
     * https://www.cnblogs.com/grandyang/p/4293853.html
     * 递归：思路是先利用DFS的思路找到最左子节点，然后回到其父节点，把其父节点和右子节点断开，将原左子结点连上父节点的右子节点上，然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作
     */
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            flatten3(root.left);
        }
        if (root.right != null) {
            flatten3(root.right);
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = tmp;
    }

    /**
     * 非递归：不用栈，自上而下移花接木
     * https://www.cnblogs.com/grandyang/p/4293853.html 法二
     * 从根节点开始出发，先检测其左子结点是否存在，如存在则将根节点和其右子节点断开，将左子结点及其后面所有结构一起连到原右子节点的位置，把原右子节点连到原左子结点最后面的右子节点之后。
     */
    public void flatten4(TreeNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            //先检测其左子结点是否存在，存在则将根节点和其右子节点断开，将左子结点及其后面所有结构一起连到原右子节点的位置，把原右子节点连到原左子结点最后面的右子节点之后
            if (root.left != null) {
                //找到左子树最右子节点
                TreeNode tmp = root.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }

                tmp.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /**
     * 非递归：用栈，思路同上
     * https://www.cnblogs.com/grandyang/p/4293853.html 法三
     */
    public void flatten6(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {
            root = stack.pop();
            if (root.left != null) {
                TreeNode tmp = root.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
    }

    /**
     * 非递归：用栈
     */
    public void flatten5(TreeNode root) {
        helper(root);
    }

    TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        TreeNode tmp = root;
        root.right = left;
        root.left = null;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
        return root;
    }
}
