package structure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    static class Node implements Comparable<Node> {
        char c;
        int fq;
        Node left, right;
        boolean isLeaf;

        public Node(char c, int fq) {
            this.c = c;
            this.fq = fq;
            isLeaf = true;
        }

        public Node(int fq, Node left, Node right) {
            this.fq = fq;
            this.left = left;
            this.right = right;
            isLeaf = false;
        }

        @Override
        public int compareTo(Node o) {
            return this.fq - o.fq;
        }
    }

    private static Map<Character, Integer> getFreqMap(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static void encode(Node node, String s, Map<Character, String> char2CodeMap) {
        if (node.isLeaf) {
            char2CodeMap.put(node.c, s);
            return;
        }
        encode(node.left, s + "0", char2CodeMap);
        encode(node.right, s + "1", char2CodeMap);
    }

    static String encode(String str) {
        char[] chars = str.toCharArray();
        //获取每个字符频率
        Map<Character, Integer> fqCnt = getFreqMap(chars);
        //按词频排序放入队列中
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : fqCnt.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }
        //构造Huffman树
        while (queue.size() != 1) {
            Node n1 = queue.poll();
            Node n2 = queue.poll();
            queue.add(new Node(n1.fq + n2.fq, n1, n2));
        }
        //根据Huffman树给字符串编码
        //存储每个字符的编码串
        Map<Character, String> char2CodeMap = new HashMap<>();
        encode(queue.poll(), "", char2CodeMap);
        //返回编码后的字符串
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(char2CodeMap.get(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "liulishuo";
        String code = encode(s);
        System.out.println(code);
    }
}