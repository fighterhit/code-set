package NewCoderProblemSet.Other.Liulishuo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chars = str.toCharArray();
        encode(chars);
    }

    private static void encode(char[] str) {
        //统计词频
        Map<Character, Integer> fqForChar = new HashMap<>();
        for (char c : str) {
            fqForChar.put(c, fqForChar.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<MyNode> priorityQueue = new PriorityQueue<>();
        for (char c : fqForChar.keySet()) {
            priorityQueue.add(new MyNode(c, fqForChar.get(c)));
        }
        while (priorityQueue.size() != 1) {
            MyNode node1 = priorityQueue.poll();
            MyNode node2 = priorityQueue.poll();
            priorityQueue.add(new MyNode(node1, node2, node1.fq + node2.fq));
        }
        Map<Character, String> encodeStr = encode(priorityQueue.poll());
        int cnt = 0;
        for (char c : str) {
            cnt += encodeStr.get(c).length();
        }
        System.out.println(cnt);
    }

    private static Map<Character, String> encode(MyNode root) {
        Map<Character, String> encodeForChar = new HashMap<>();
        encode(root, "", encodeForChar);
        return encodeForChar;
    }

    private static void encode(MyNode node, String encode, Map<Character, String> encodeForChar) {
        if (node.leaf) {
            encodeForChar.put(node.ch, encode);
            return;
        }
        encode(node.left, encode + "0", encodeForChar);
        encode(node.right, encode + "1", encodeForChar);
    }

    static class MyNode implements Comparable<MyNode> {
        char ch;
        int fq;
        boolean leaf;
        MyNode left, right;

        public MyNode(char ch, int f) {
            this.ch = ch;
            fq = f;
            leaf = true;
        }

        public MyNode(MyNode left, MyNode right, int f) {
            this.left = left;
            this.right = right;
            this.fq = f;
            leaf = false;
        }

        @Override
        public int compareTo(MyNode o) {
            return this.fq - o.fq;
        }
    }
}
