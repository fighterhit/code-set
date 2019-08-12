package leetcode.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 对比 H126_WordLadderII，M279_PerfectSquares
 */
public class M127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        LinkedList<String> q = new LinkedList<>();
        q.add(beginWord);
        int res = 0;
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                if (w.equals(endWord)) {
                    return res + 1;
                }
                for (int j = 0; j < w.length(); j++) {
                    StringBuilder newWord = new StringBuilder(w);
                    for (int k = 'a'; k <= 'z'; k++) {
                        newWord.setCharAt(i, (char) k);
                        if (wordList.contains(newWord.toString()) && !newWord.equals(w)) {
                            q.add(newWord.toString());
                            wordList.remove(newWord.toString());
                        }
                    }
                }
            }
            res++;
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int n = wordList.size();
        int start = n - 1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (wordList.get(i).equals(endWord)) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            return 0;
        }
        List<Integer>[] G = buildG(wordList);
        return getShortestPath(start, n, G, end);
    }

    private int getShortestPath(int start, int n, List<Integer>[] G, int end) {
        boolean marked[] = new boolean[n];
        marked[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (Integer adj : G[cur]) {
                    if (adj == end) {
                        return level;
                    }
                    if (marked[adj]) {
                        continue;
                    }
                    marked[adj] = true;
                    queue.add(adj);
                }
            }
        }
        //注意返回值，表示不能到达
        return 0;
    }

    //生成每个字符串的邻接字符串 —— "图"
    private List<Integer>[] buildG(List<String> wordList) {
        int n = wordList.size();
        List<Integer>[] G = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (isConnect(wordList, i, j))
                    G[i].add(j);
            }
        }
        return G;
    }

    boolean isConnect(List<String> wordList, int i, int j) {
        String s1 = wordList.get(i);
        String s2 = wordList.get(j);
        int diffCnt = 0;
        for (int k = 0; k < s1.length() && diffCnt <= 1; k++) {
            if (s1.charAt(k) != s2.charAt(k)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }
}
