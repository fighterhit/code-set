package structure;

import sword.TreeNode;

import java.util.*;

/**
 * 二叉树遍历
 * https://www.cnblogs.com/gl-developer/p/7259251.html
 *
 * @author Fighter Created on 2018/4/27.
 */
public class BinaryTreeTraverse {

    public static void visit(TreeNode node) {
        System.out.print(node.val + " ");
    }

    /*****************************递归版***********************************/
    /**
     * 前序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node != null) {
            visit(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            visit(node);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            visit(node);
        }
    }

    /*****************************非递归版***********************************/
    //https://leetcode.com/problems/binary-tree-postorder-traversal/

    /**
     * 前序遍历
     * 使用栈，时间复杂度O(n)，空间复杂度O(n)
     *
     * @param root
     * @return 遍历结果的列表
     */
    public List<Integer> preorderTraversal(TreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        //注意，只有前序遍历初始时将root压入栈中
        stack.push(root);
        while (!stack.isEmpty()) {

            TreeNode<Integer> node = stack.pop();
            result.add(node.val);
            //先压栈右孩子 再压栈 左孩子，保证左子树先遍历
            //先进后遍历
            if (node.right != null) {
                stack.push(node.right);
            }
            //先进先遍历
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * 中序遍历
     * 使用栈，时间复杂度 O(n)，空间复杂度 O(n)
     *
     * @param root
     * @return 遍历结果的列表
     */

    public List<Integer> inorderTraversal(TreeNode<Integer> root) {

        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }

        return result;
    }


    /**
     * 后序遍历
     * 使用栈，时间复杂度 O(n)，空间复杂度 O(n)
     *
     * @param root
     * @return 遍历结果的列表
     */
    public static List<Integer> postorderTraversal(TreeNode<Integer> root) {

        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode<Integer>> stack = new Stack<>();

        TreeNode<Integer> node = root;
        //记录上个访问的节点
        TreeNode<Integer> preNode = null;

        while (!stack.isEmpty() || node != null) {
            // 往左下走
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                // 一个根节点被访问的前提是：无右子树或右子树已被访问过
                TreeNode<Integer> rNode = stack.peek().right;
                if (rNode == null || rNode == preNode) {
                    node = stack.pop();
                    // 记录刚被访问过的节点
                    result.add(node.val);
                    //注意标记上次被访问过
                    preNode = node;
                    //表示这颗树被访问过
                    node = null;
                } else {
                    node = rNode;
                }
            }
        }

        return result;
    }

    //前序遍历为 root -> left -> right，后序遍历为 left -> right -> root。
    // 可以修改前序遍历成为 root -> right -> left，那么这个顺序就和后序遍历正好相反。
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add((Integer) node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }


    public static List<Integer> postorderTraversal3(TreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
        stack.push(node);
        //记录上个访问的节点
        TreeNode<Integer> preNode = null;
        while (!stack.isEmpty()) {
            node = stack.peek();
            if ((node.left == null && node.right == null) ||
                    (preNode != null && (preNode == node.left || preNode == node.right))) {
                result.add(node.val);
                stack.pop();
                preNode = node;
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return result;
    }

    /**
     * 层序遍历
     * 使用队列，时间复杂度 O(n)，空间复杂度 O(n)
     *
     * @param
     * @return 遍历结果的列表
     */

    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                TreeNode<Integer> node = queue.poll();
                ls.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(ls);
        }

        return result;
    }

    //根据输入整形数组（完全二叉树）如[1,2,3,5,null,null,7]，创建二叉树
    //递归创建
    public TreeNode createTree(Integer[] vals, int index) {
        TreeNode root = new TreeNode(vals[index]);
        root.left = createTree(vals, 2 * index + 1);
        root.right = createTree(vals, 2 * index + 2);
        return root;
    }

    //非递归创建
    public TreeNode createTree2(TreeNode[] node) {
        for (int i = 0; i < node.length; i++) {
            if (2 * i + 1 < node.length) {
                node[i].left = node[2 * i + 1];
            }
            if (2 * i + 2 < node.length) {
                node[i] = node[2 * i + 2];
            }
        }
        return node[0];
    }

    public static void main(String[] args) {

        // 构造二叉树
        //   1
        //  / \
        //  2  3
        // /\  \
        // 4 5  6
        //  / \
        //  7  8

        /*
        前序遍历：1  2  4  5  7  8  3  6
        中序遍历：4  2  7  5  8  1  3  6
        后序遍历：4  7  8  5  2  6  3  1
        层次遍历：1  2  3  4  5  6  7  8
         */

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;
        node5.right = node8;

        //前序遍历
        preOrder(root);
        System.out.println();
        //中序遍历
        inOrder(root);
        System.out.println();
        //后续遍历
        postOrder(root);
        System.out.println();
        System.out.println(postorderTraversal3(root));
        ;

    }
}
