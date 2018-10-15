package structure.AVL;

import structure.SetMap.FileOperation;
import structure.SetMap.Map.BSTMap;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V value;
        private int height;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 获得节点node的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的  根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            node = new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        node.height = 1 + Math.max(node.left.height, node.right.height);
        int balanceFactor = getBalanceFactor(node);

        //右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            node = rightRotate(node);
        }

        //左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            node = leftRotate(node);
        }
        // return current node
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node) {

        return null;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node node) {


        return null;
    }


    // 返回以node为根节点的二分搜索树中，key所在的节点
    public Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = newValue;
        } else {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minmum(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("BST is empty");
        }
        if (node.left == null) {
            return node;
        } else {
            return minmum(node.left);
        }
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("BST is empty");
        }
        //递归终止条件：遍历到左子结点为空的节点
        if (node.left != null) {
            Node rightNode = node.right;
            node = null;
            size--;
            //将右子结点接上当前节点的父结点
            return rightNode;
        }

        return removeMin(node.left);
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minmum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\SetMap\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
