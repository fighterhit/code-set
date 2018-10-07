package leetcode.middle;

import java.util.TreeMap;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 * @author Fighter Created on 2018/10/7.
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
