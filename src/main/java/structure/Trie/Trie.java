package structure.Trie;

import java.util.TreeMap;

/**
 * Trie 字典树实现
 *
 * @author Fighter Created on 2018/10/7.
 */
public class Trie {

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

    // 向Trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) != null) {
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
}
