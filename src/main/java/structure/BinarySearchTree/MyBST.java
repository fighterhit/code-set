package structure.BinarySearchTree;

/**
 * 手写二叉搜索树（Java实现）
 * https://blog.csdn.net/csdn_blog_lcl/article/details/78186572
 */
public class MyBST {

    Node root;
    int count;

    class Node {
        //节点索引和值
        int key, val;
        Node left, right;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //向BST插入节点，并返回根节点
    Node insert(Node root, int k, int v) {
        if (root == null) {
            count++;
            return new Node(k, v);
        }
        if (root.key == k) {
            root.val = v;
        } else if (root.key > k) {
            root.left = insert(root.left, k, v);
        } else {
            root.right = insert(root.right, k, v);
        }
        return root;
    }

    //查找索引为key的节点
    Node search(Node root, int k) {
        if (root == null) {
            return null;
        }
        if (root.key == k) {
            return root;
        } else if (root.key > k) {
            return search(root.left, k);
        } else {
            return search(root.right, k);
        }
    }

    //删除树中key最小的节点
    //删除时需要判断此要删除的节点是否还有右子树，若有需要将右子树替换到被删除的位置
    Node delMin(Node root) {
        if (root.left == null) {
            Node node = root.right;
            count--;
            return node;
        }
        root.left = delMin(root.left);
        return root;
    }

    //删除树索引中最大的节点
    Node delMax(Node root) {
        if (root.right == null) {
            Node node = root.left;
            count--;
            return node;
        }
        root.right = delMax(root.right);
        return root;
    }

    //删除树中任意节点
    Node delNode(Node root, int k) {
        if (root == null) {
            return null;
        }
        if (root.key > k) {
            root.left = delNode(root.left, k);
            return root;
        } else if (root.key < k) {
            root.right = delNode(root.right, k);
            return root;
        } else {
            //找到该节点，判断该节点有无左右子树
            if (root.left == null) {
                Node node = root.right;
                count--;
                return node;
            }
            if (root.right == null) {
                Node node = root.left;
                count--;
                return node;
            }
            //左右子树都不为空的情况下,寻找右子树中最小节点替代要删除 的节点
            //寻找要删除节点的右子树中索引最小的节点
            Node successor = findMin(root.right);
            //删除要删除节点的右子树中索引最小的节点，即删除上述找到的 successor 节点
            successor.right = delMin(root.right);
            successor.left = root.left;
            return successor;
        }
    }

    private Node findMin(Node right) {
        if (root.left != null) {
            return findMin(root.left);
        }
        return root;
    }

    int size() {
        return count;
    }

    boolean isEmpty() {
        return count == 0;
    }

    //前中后序、层序遍历略
}
