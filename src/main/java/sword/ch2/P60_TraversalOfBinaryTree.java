package sword.ch2;

import sword.TreeNode;

import java.util.*;

public class P60_TraversalOfBinaryTree {

    static List<Integer> list = new ArrayList<>();

    //前序遍历递归版
    public static List<Integer> preorderRecursively2(TreeNode<Integer> node) {
        if (node == null)
            return list;
        list.add(node.val);
        preorderRecursively(node.left);
        preorderRecursively(node.right);
        return list;
    }

    //前序遍历递归版
    public static List<Integer> preorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.add(node.val);
        list.addAll(preorderRecursively(node.left));
        list.addAll(preorderRecursively(node.right));
        return list;
    }

    //中序遍历递归版
    public static List<Integer> inorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(inorderRecursively(node.left));
        list.add(node.val);
        list.addAll(inorderRecursively(node.right));
        return list;
    }

    //后序遍历递归版
    public static List<Integer> postorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null)
            return list;
        list.addAll(postorderRecursively(node.left));
        list.addAll(postorderRecursively(node.right));
        list.add(node.val);
        return list;
    }

    //前序遍历非递归版
    public static List<Integer> preorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        if (node == null)
            return list;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //中序遍历非递归版
    public static List<Integer> inorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                list.add(stack.peek().val);
                cur = stack.pop().right;
            }
        }
        return list;
    }

    //前序遍历非递归版
    public static List<Integer> postorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        //prevVisted用于区分是从左子树还是右子树返回的
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        TreeNode<Integer> prevVisted = null;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek().right;
                if (cur != null && cur != prevVisted) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    prevVisted = stack.pop();
                    list.add(prevVisted.val);
                    cur = null;
                }
            }
        }
        return list;
    }

    //层序遍历
    public static List<Integer> levelorder(TreeNode<Integer> node) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        TreeNode<Integer> temp = null;
        if (node == null)
            return list;
        queue.add(node);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return list;
    }

    static boolean flag = true;
    static List<Integer> res = new ArrayList<>();

    //层序遍历递归
    public static List<Integer> levelorder2(TreeNode<Integer> root) {
        if (root == null) {
            return res;
        }
        int level = 1;
        //每次从 root 开始 DFS，相当于按每种深度都从根节点遍历一次
        while (flag) {
            flag = false;
            levelorder2helper(root, level);
            level++;
        }
        return res;
    }

    public static void levelorder2helper(TreeNode<Integer> root, int level) {
        if (root == null)
            return;
        if (level == 1) {
            res.add(root.val);
            if (root.left != null || root.right != null) {
                flag = true;
            }
            return;
        }
        //往下一层 level - 1
        levelorder2helper(root.left, level - 1);
        levelorder2helper(root.right, level - 1);
    }

    //层序遍历递归：思想同 levelOrder2，对每一层从根节点深度遍历，没往下一层，level - 1，当减到 1，输出
    public static List<Integer> levelOrder3(TreeNode root) {
        int depth = getDepth(root);
        for (int i = 1; i <= depth; i++) {
            levelOrder33(root, i);
        }
        return res;
    }

    //二叉树层序遍历——递归版：DFS
    public static void levelOrder33(TreeNode<Integer> root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            res.add(root.val);
            return;
        }
        levelOrder33(root.left, level - 1);
        levelOrder33(root.right, level - 1);
    }

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        return Math.max(l, r) + 1;
    }

    public static void main(String[] args) {
        //            1
        //              \
        //               2
        //              /
        //             3
        //pre->123  in->132   post->321  level->123
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.right = new TreeNode<Integer>(2);
        root.right.left = new TreeNode<Integer>(3);
        List<Integer> list_preorderRecursively = preorderRecursively2(root);
        System.out.print("preorderRecursively: " + '\t');
        System.out.println(list_preorderRecursively.toString());

        List<Integer> list_inorderRecursively = inorderRecursively(root);
        System.out.print("inorderRecursively: " + '\t');
        System.out.println(list_inorderRecursively.toString());

        List<Integer> list_postorderRecursively = postorderRecursively(root);
        System.out.print("postorderRecursively: " + '\t');
        System.out.println(list_postorderRecursively.toString());
        System.out.println();


        List<Integer> list_preorderIteratively = preorderIteratively(root);
        System.out.print("preorderIteratively: " + '\t');
        System.out.println(list_preorderIteratively.toString());

        List<Integer> list_inorderIteratively = inorderIteratively(root);
        System.out.print("inorderIteratively: " + '\t');
        System.out.println(list_inorderIteratively.toString());

        List<Integer> list_postorderIteratively = postorderIteratively(root);
        System.out.print("postorderIteratively: " + '\t');
        System.out.println(list_postorderIteratively.toString());
        System.out.println();

        List<Integer> list_levelorder = levelorder(root);
        System.out.print("levelorder: " + '\t');
        System.out.println(list_levelorder.toString());
    }
}
