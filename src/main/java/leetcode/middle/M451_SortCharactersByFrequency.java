package leetcode.middle;

import java.util.*;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * Example 1:
 * Input:
 * "tree"
 * Output:
 * "eert"
 * <p>
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * <p>
 * Example 2:
 * Input:
 * "cccaaa"
 * Output:
 * "cccaaa"
 * <p>
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * <p>
 * Example 3:
 * Input:
 * "Aabb"
 * Output:
 * "bbAa"
 * <p>
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * 参考 M347_TopKFrequentElements
 */
public class M451_SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        //直接用库按频率值排序
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : list) {
            //拿到排序后每个key对应频率值，出现几次就输出几次
            int count = entry.getValue();
            char c = entry.getKey();
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //优先队列解决
    public String frequencySort2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //注意两者不同
        //PriorityQueue<Map<Character,Integer>> priorityQueue = new PriorityQueue<>((a,b) ->(b.get(b.k)));
        //优先队列设置比较器，按值从大到小排序
        PriorityQueue<Map.Entry<Character, Integer>> priorityQueue = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            priorityQueue.add(entry);
        }
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Map.Entry<Character, Integer> entry = priorityQueue.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    //与上述不同，出现次数作为key值
    //设置若干（初始化字符串长度）个桶，每个桶是一个列表存储出现频率相同的数。桶的下标表示数出现的频率，即第 i 个桶中存储的数出现的频率为 i。
    //把数都放到桶之后，从后向前遍历桶，每个元素输出 i 遍，i 是桶下标且表示元素出现次数。
    public String frequencySort3(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //
        List<Character>[] bucket = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i > 0; i--) {
            if (bucket[i] != null) {
                for (Character key : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(key);
                    }
                }
            }
        }
        return sb.toString();
    }
}
