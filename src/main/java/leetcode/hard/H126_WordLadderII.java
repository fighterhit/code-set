package leetcode.hard;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * <p>
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * @author Fighter.
 */
public class H126_WordLadderII {
    static List<List<String>> res = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
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
            return res;
        }
        List<Integer>[] G = buildG(wordList);
        getShortestPath(start, n, G, end, wordList);
        return res;
    }

    private void getShortestPath(int start, int n, List<Integer>[] G, int end, List<String> wordList) {
        boolean marked[] = new boolean[n];
        marked[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
//        List<String> ls = new ArrayList<>();
//        ls.add(wordList.get(start));
        int pre = start;
        map.put(start, -1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
//                int lsEndIndex = ls.size();
//                ls.add(wordList.get(cur));
                for (Integer adj : G[cur]) {
                    if (adj == end) {
                        map.put(adj, cur);
//                        ls.add(wordList.get(adj));
                        List<String> ls = new ArrayList<>();
                        int tmp = end;
                        while (tmp > 0) {
                            ls.add(wordList.get(tmp));
                            tmp = map.get(tmp);
                        }
                        res.add(ls);
                        continue;
                    }
                    if (marked[adj]) {
                        continue;
                    }
                    map.put(adj, pre);
                    marked[adj] = true;
                    queue.add(adj);
                }
//                ls = ls.subList(0, lsEndIndex);
            }
        }
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

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        new H126_WordLadderII().findLadders("hit", "cog", ls);
        System.out.println(res);
    }

}
