package structure.SetMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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

    /**************二分搜索树递归遍历******************/
    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
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

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
//        System.out.println(node);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
//        System.out.println(node);
    }

    /**************二分搜索树非递归前序遍历******************/
    //中序和后序遍历不常用
    public void preOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    //二分搜索树层序遍历
    public void levelOrder() {
        if (root == null) {
            return;
        }
        List<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).poll();
            System.out.println(node.e);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        //空树，即0个节点
        if (root == null) {//size == 0
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {
        //若为空树，则 minimum() 为抛异常，不会执行后面的 removeMin()
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        //递归终止条件：遍历到左子结点为空的节点
        if (node.left == null) {
            //保存右子结点
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        //注意赋值
        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        //注意赋值
        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        //***注意返回的节点为新的根节点，当删除节点为根节点时
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        //递归终止条件
        //没找到
        if (node == null) {
            return null;
        }
        //指定元素小于当前节点
        if (e.compareTo(node.e) < 0) {
            //注意节点衔接
            node.left = remove(node.left, e);
            return node;
        }
        if (e.compareTo(node.e) > 0) {
            //注意节点衔接
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.e
            //左子树为空，右节点顶替当前节点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //右子树为空，左子树顶替当前节点
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //找到右子树中的后继节点
            Node successor = minimum(node.right);
            //删除后继节点后的右子树
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    //层序遍历 一般使用非递归
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
        res.append(generateDepthStirng(depth) + node.e + "\n");
        generateBSTStirng(node.left, depth + 1, res);
        generateBSTStirng(node.right, depth + 1, res);
    }

    private String generateDepthStirng(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("-");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        /*
        BST<Integer> bst = new BST<>();
        int[] arr = new int[]{5, 3, 6, 8, 2, 4, 6};
        for (int i : arr) {
            bst.add(i);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////

        //遍历测试
       bst.preOrder();
        System.out.println(bst);

        bst.inOrder();
        System.out.println();

        bst.postOrder();
        System.out.println();

        bst.preOrderNR();
        System.out.println();

        bst.levelOrder();
        System.out.println();*/

/*        //删除最小/最大元素测试
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            int tmpMin = bst.removeMin();
            nums.add(tmpMin);
        }

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new IllegalArgumentException("Error!");
            }
        }
        System.out.println("removeMin test completed.");

        // test removeMax
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                throw new IllegalArgumentException("Error!");
            }
        }
        System.out.println("removeMax test completed.");*/

        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 10000;

        for(int i = 0 ; i < n ; i ++) {
            bst.add(random.nextInt(n));
        }

        // 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的

        // order数组中存放[0...n)的所有元素
        Integer[] order = new Integer[n];
        for( int i = 0 ; i < n ; i ++ ) {
            order[i] = i;
        }
        // 打乱order数组的顺序
        shuffle(order);

        // 乱序删除[0...n)范围里的所有元素
        for( int i = 0 ; i < n ; i ++ ) {
            if(bst.contains(order[i])){
                bst.remove(order[i]);
                System.out.println("After remove " + order[i] + ", size = " + bst.getSize() );
            }
        }

        // 最终整个二分搜索树应该为空
        System.out.println(bst.getSize());
    }

    // 打乱数组顺序
    private static void shuffle(Object[] arr){

        for(int i = arr.length - 1 ; i >= 0 ; i --){
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }
}
