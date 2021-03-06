package structure.Trie;

import structure.SetMap.FileOperation;
import structure.SetMap.Set.BSTSet;

import java.util.ArrayList;
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

    // 向Trie中添加一个新的单词word，非递归方法
    public void add(String word) {
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
    public boolean contains(String word) {
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
    public boolean isPrefix(String prefix) {
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

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\SetMap\\pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();
            BSTSet<String> set = new BSTSet<>();

            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // --------------------------------------------------------

            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
