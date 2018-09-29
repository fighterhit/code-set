package structure.BinarySearchTree;

public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
        }
    }


    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //用户调用的方法，向二分搜索树中添加新元素e
    public void add(E e) {
       /*
       if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
        */

        root = add(root, e);
    }

    //向以node为根节点的二分搜索树中添加节点
    private Node add(Node node, E e) {

       /* //以下为二分搜索树添加节点的递归终止条件
        if (e.equals(node.e)) {
            return;
        }
        //e 小于 node.e，将 node 插入 node 的左子树
        //因为不是基础类型 不能用< > =
        else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }*/


        if (node == null) {
            size++;
            return new Node(e);
        }

        //递归
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }

        //不能用else 因为当等于0时 相当于什么都不做
        else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
//        System.out.println(node.e);
//        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);

      /* 等价于下述代码
        if(node!=null){
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }*/
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTStirng(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以root为根节点的二叉搜索树字符串
     *
     * @param node  根节点
     * @param depth 树深度  根节点深度为0 根节点左右子树深度为1 以此类推...
     * @param res
     */
    private void generateBSTStirng(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthStirng(depth) + "null\n");
            return;
        }
        res.append(generateDepthStirng(depth)  + node.e + "\n");
        generateBSTStirng(node.left,depth + 1,res);
        generateBSTStirng(node.right,depth + 1,res);
    }

    private String generateDepthStirng(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("-");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] arr = new int[]{5, 3, 6, 8, 2, 4, 6};
        for (int i : arr) {
            bst.add(i);
        }
        bst.preOrder();
        System.out.println(bst);
    }
}
