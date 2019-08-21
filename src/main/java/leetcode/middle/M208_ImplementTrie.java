package leetcode.middle;

import java.util.TreeMap;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 * @author Fighter Created on 2018/10/7.
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E6%A0%91.md#11-%E7%BB%9F%E8%AE%A1%E5%B7%A6%E5%8F%B6%E5%AD%90%E8%8A%82%E7%82%B9%E7%9A%84%E5%92%8C
 */
public class M208_ImplementTrie {

}

class Trie {

    class Node {
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    //单词数
    private int size;

    public Trie() {
        this.root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // 向Trie中添加一个新的单词word，非递归方法
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        //如果trie中不存在该单词才为size+1
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 从Trie中查询一个单词word，非递归方法
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        //当trie中存在pandas但查询pan时，应检查最后停留节点是否是单词结尾
        return cur.isWord;
    }

    //查询是否在Trie中有单词以prefix为前缀
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}

class Trie2 {

    private class Node {
        Node[] childs = new Node[26];
        boolean isLeaf;
    }

    private Node root = new Node();

    public Trie2() {
    }

    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node node) {
        //递归边界
        if (node == null) return;
        if (word.length() == 0) {
            node.isLeaf = true;
            return;
        }

        int index = indexForChar(word.charAt(0));
        if (node.childs[index] == null) {
            node.childs[index] = new Node();
        }
        //递归
        insert(word.substring(1), node.childs[index]);
    }

    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, Node node) {
        if (node == null) return false;
        if (word.length() == 0) return node.isLeaf;
        int index = indexForChar(word.charAt(0));
        return search(word.substring(1), node.childs[index]);
    }

    public boolean startsWith(String prefix) {
        return startWith(prefix, root);
    }

    private boolean startWith(String prefix, Node node) {
        if (node == null) return false;
        if (prefix.length() == 0) return true;
        int index = indexForChar(prefix.charAt(0));
        return startWith(prefix.substring(1), node.childs[index]);
    }

    private int indexForChar(char c) {
        return c - 'a';
    }
}
