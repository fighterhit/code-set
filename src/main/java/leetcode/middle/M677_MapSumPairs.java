package leetcode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a MapSum class with insert, and sum methods.
 * <p>
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 * <p>
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 * <p>
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 *
 * @author Fighter Created on 2018/10/7.
 */
public class M677_MapSumPairs {
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));

    }
}

class MapSum {

    Map<String, Integer> set;
    Map<String, Integer> prefixValMap;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        set = new HashMap<>();
        prefixValMap = new HashMap<>();
    }

    public void insert(String key, int val) {
        if (set.containsKey(key)) {
            int old = set.get(key);
            for (int i = 1; i <= key.length(); i++) {
                String sub = key.substring(0, i);
                //除去原来的值，加上新值
                prefixValMap.put(sub, prefixValMap.get(sub) - old + val);
            }
        } else {
            set.put(key, val);
            for (int i = 1; i <= key.length(); i++) {
                String sub = key.substring(0, i);
                prefixValMap.put(sub, prefixValMap.getOrDefault(sub, 0) + val);
            }
        }
    }

    public int sum(String prefix) {
        return prefixValMap.getOrDefault(prefix, 0);
    }
}
/*
//使用自定义trie树
class MapSum {
    class Node {
        Map<Character, Node> next;
        int score;
        public Node(int score) {
            this.next = new TreeMap<>();
            this.score = score;
        }
        public Node() {
            this(0);
        }
    }

    private Node root;

    */
/**
 * Initialize your data structure here.
 *//*

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.score = val;
    }

    public int sum(String prefix) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        //找到prefix最后一个节点，开始以此为根遍历所有单词
        return sum(cur);
    }

    private int sum(Node cur) {
        //递归终止条件：到达叶子节点
        */
/*等价于下面
        if (cur.next.size() == 0) {
            return cur.score;
        }
        *//*

        int res = cur.score;
        for (Character c : cur.next.keySet()) {
            res += sum(cur.next.get(c));
        }
        return res;
    }
}
*/

